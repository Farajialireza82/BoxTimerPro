package com.cromulent.box_timer.presentation.timer_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewModel(
    settingsRepository: SettingsRepository,
    val audioPlayer: AudioPlayer,
    val systemEngine: SystemEngine,
) : ViewModel() {

    private val _state = MutableStateFlow(TimerState())
    val state = _state.asStateFlow()

    lateinit var appSettings: AppSettings


    init {
        viewModelScope.launch {
            settingsRepository.getTimerSettings()?.toTimerState()?.let { timerState ->
                _state.update {
                    timerState
                }
            }
            appSettings = settingsRepository.getAppSettings() ?: AppSettings()
        }
        start()
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
        toggleKeepScreenOn(appSettings.keepScreenOnEnabled)

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
        toggleKeepScreenOn(false)
        timerJob?.cancel()
        timerJob = null
        _state.update { it.copy(isTimerRunning = false, timerMessage = "Paused") }
    }

    private fun reset() {
        toggleKeepScreenOn(false)
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
            countDownAlert()
            delay(1000L)
        }
    }

    private suspend fun runPhase(
        duration: Long,
        phase: TimerPhase,
        defaultMessage: String,
        showCountdownAtEnd: Boolean = false
    ) {
        _state.update { it.copy(phase = phase, timerMessage = defaultMessage) }
        if (phase == TimerPhase.FIGHT && state.value.currentTime == 0L) startRoundAlert()
        while (_state.value.currentTime < duration && _state.value.isTimerRunning) {
            delay(10L)
            _state.update { state ->
                val newTime = state.currentTime + 10L
                val remaining = duration - newTime
                val msg = if (showCountdownAtEnd && remaining in 1000L..4000L) {
                    "Get Ready"
                } else defaultMessage
                if (remaining == 3000L) countDownAlert()
                if (remaining == 2000L) countDownAlert()
                if (remaining == 1000L) countDownAlert()
                if (phase == TimerPhase.FIGHT) {
                    if (remaining == 0L) endRoundAlert()
                }
                state.copy(currentTime = newTime, timerMessage = msg)
            }
        }
    }

    private fun endRoundAlert(){
        vibratePhone(1000L)
        playAudio(appSettings.endRoundAudioFile.uri)
    }

    private fun startRoundAlert(){
        vibratePhone(700L)
        playAudio(appSettings.startRoundAudioFile.uri)
    }

    private fun countDownAlert() {
        vibratePhone(500L)
        playAudio(appSettings.countDownAudioFile.uri)
    }

    private fun vibratePhone(duration: Long = 1000L){
        if(appSettings.isVibrationEnabled) systemEngine.vibrate(duration)
    }

    private fun playAudio(uri: String) {
        if (appSettings.muteAllSounds) return
        audioPlayer.playSound(uri)
    }

    private fun toggleKeepScreenOn(enabled: Boolean){
        systemEngine.keepScreenOn(enabled)
    }

    override fun onCleared() {
        timerJob?.cancel()
        audioPlayer.release()
        toggleKeepScreenOn(false)
        super.onCleared()
    }
}