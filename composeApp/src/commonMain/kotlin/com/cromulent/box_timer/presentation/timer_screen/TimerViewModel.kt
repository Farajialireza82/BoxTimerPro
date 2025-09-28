package com.cromulent.box_timer.presentation.timer_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.domain.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class TimerViewModel(
    settingsRepository: SettingsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TimerState())
    val state = _state.asStateFlow()



    init {
        viewModelScope.launch {
            settingsRepository.getTimerSettings()?.toTimerState()?.let { timerState ->
                _state.update {
                    timerState
                }
            }
        }
    }

    private var timerJob: Job? = null

    fun onAction(action: TimerActions) {
        when (action) {
            TimerActions.StartTimer -> start()
            TimerActions.PauseTimer -> pause()
            TimerActions.ResetTimer -> reset()
        }
    }

    private fun start() {
        if (_state.value.isTimerRunning) return

        _state.update { it.copy(isTimerRunning = true) }

        timerJob = viewModelScope.launch {
            var round = _state.value.currentRound

            // loop through all rounds
            while (round <= _state.value.totalRounds && _state.value.isTimerRunning) {
                // Countdown before fight
                if (_state.value.phase == TimerPhase.IDLE || _state.value.phase == TimerPhase.COUNTDOWN) {
                    runCountdown(3)
                }

                // Fight phase
                runPhase(_state.value.roundDuration, TimerPhase.FIGHT, "Fight")
                if (!_state.value.isTimerRunning) break

                // Last round → stop
                if (round == _state.value.totalRounds) {
                    reset()
                    break
                }

                // Rest phase (with "Get Ready" in last 3 seconds)
                runPhase(
                    _state.value.restDuration,
                    TimerPhase.REST,
                    "Rest",
                    showCountdownAtEnd = true
                )
                if (!_state.value.isTimerRunning) break

                // Next round
                round++
                _state.update { it.copy(currentRound = round) }
            }
        }
    }

    private fun pause() {
        timerJob?.cancel()
        timerJob = null
        _state.update { it.copy(isTimerRunning = false, timerMessage = "Paused") }
    }

    private fun reset() {
        timerJob?.cancel()
        timerJob = null
        _state.update {
            it.copy(
                isTimerRunning = false,
                currentRound = 1,
                currentTime = 0L,
                phase = TimerPhase.IDLE,
                timerMessage = "Ready"
            )
        }
    }

    // --- HELPERS ---

    private suspend fun runCountdown(seconds: Int) {
        _state.update { it.copy(phase = TimerPhase.COUNTDOWN, currentTime = 0L) }
        for (i in seconds downTo 1) {
            if (!_state.value.isTimerRunning) break
            _state.update { it.copy(timerMessage = "Get Ready: $i") }
            delay(1000L)
        }
    }

    private suspend fun runPhase(
        duration: Long,
        phase: TimerPhase,
        defaultMessage: String,
        showCountdownAtEnd: Boolean = false
    ) {
        _state.update { it.copy(phase = phase, currentTime = 0L, timerMessage = defaultMessage) }

        while (_state.value.currentTime < duration && _state.value.isTimerRunning) {
            delay(10L)
            _state.update { state ->
                val newTime = state.currentTime + 10L
                val remaining = duration - newTime
                val msg = if (showCountdownAtEnd && remaining in 1000L..3000L) {
                    "Get Ready"
                } else defaultMessage
                state.copy(currentTime = newTime, timerMessage = msg)
            }
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}