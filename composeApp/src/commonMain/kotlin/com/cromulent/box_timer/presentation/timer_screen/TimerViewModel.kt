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
    private var isResting = false


    init {
        viewModelScope.launch {
            settingsRepository.getTimerSettings()?.toTimerState()?.let { timerState ->
                _state.update {
                    timerState
                }
            }
            appSettings = settingsRepository.getAppSettings() ?: AppSettings()
            start()
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
        if (_state.value.isPaused.not()) {
            _state.update {
                it.copy(
                    remainingTime = _state.value.roundDuration,
                    progress = 0f
                )
            }
        }
        _state.update { it.copy(isPaused = false, isTimerRunning = true) }
        toggleKeepScreenOn(appSettings.keepScreenOnEnabled)


        timerJob = viewModelScope.launch {

            while (_state.value.currentRound <= _state.value.totalRounds && _state.value.isTimerRunning) {

                if (_state.value.currentRound == 1 && _state.value.remainingTime == _state.value.roundDuration) {
                    runTimer(
                        duration = 2000L,
                        delay = 1000L,
                        callBack = { currentTime, remainingTime ->
                            countDownAlert()
                            _state.update {
                                it.copy(
                                    timerMessage = "Get Ready: ${
                                        remainingTime.toString().take(1).toInt() + 1
                                    }"
                                )
                            }
                        }
                    )
                }


                if (isResting.not()) {
                    if (_state.value.remainingTime == _state.value.roundDuration) startRoundAlert()
                    _state.update { it.copy(timerMessage = "Fight") }
                    runTimer(
                        duration = _state.value.remainingTime,
                        delay = 10L,
                        callBack = { currentTime, remainingTime ->
                            _state.update {
                                it.copy(
                                    remainingTime = remainingTime,
                                    progress = 1f - _state.value.remainingTime / state.value.roundDuration.toFloat()
                                )
                            }
                            if (remainingTime == 3000L) countDownAlert()
                            if (remainingTime == 2000L) countDownAlert()
                            if (remainingTime == 1000L) countDownAlert()
                        }
                    )
                    endRoundAlert()
                }

                if (_state.value.currentRound != _state.value.totalRounds) {
                    _state.update { it.copy(timerMessage = "Rest", remainingTime = if(isResting) it.remainingTime else it.restDuration) }
                    isResting = true
                    runTimer(
                        duration = _state.value.remainingTime,
                        delay = 10L,
                        callBack = { currentTime, remainingTime ->
                            _state.update {
                                it.copy(
                                    remainingTime = remainingTime,
                                    progress = 1f - (_state.value.remainingTime.toFloat() / state.value.restDuration.toFloat())
                                )
                            }
                            if (remainingTime == 3000L) countDownAlert()
                            if (remainingTime == 2000L) countDownAlert()
                            if (remainingTime == 1000L) countDownAlert()
                        }
                    )
                    isResting = false
                    _state.update { it.copy(currentRound = it.currentRound + 1) }
                    _state.update {
                        it.copy(
                            remainingTime = _state.value.roundDuration,
                            progress = 0f
                        )
                    }
                } else {
                    reset()
                    break
                }
            }


        }
    }

    private fun pause() {
        toggleKeepScreenOn(false)
        timerJob?.cancel()
        timerJob = null
        _state.update { it.copy(isTimerRunning = false, timerMessage = "Paused", isPaused = true) }
    }

    private fun reset() {
        toggleKeepScreenOn(false)
        timerJob?.cancel()
        timerJob = null
        _state.update {
            it.copy(
                isTimerRunning = false,
                currentRound = 1,
                progress = 0f,
                isPaused = false,
                remainingTime = it.roundDuration,
                timerMessage = "Ready"
            )
        }
    }

    private suspend fun runTimer(
        duration: Long,
        delay: Long = 10L,
        callBack: (Long, Long) -> Unit,
    ) {
        var currentTime = 0L
        while (currentTime <= duration) {
            callBack(currentTime, duration - currentTime)
            delay(delay)
            currentTime = currentTime + delay
        }
    }


    private fun endRoundAlert() {
        vibratePhone(1000L)
        playAudio(appSettings.endRoundAudioFile.uri)
    }

    private fun startRoundAlert() {
        vibratePhone(700L)
        playAudio(appSettings.startRoundAudioFile.uri)
    }

    private fun countDownAlert() {
        vibratePhone(500L)
        playAudio(appSettings.countDownAudioFile.uri)
    }

    private fun vibratePhone(duration: Long = 1000L) {
        if (appSettings.isVibrationEnabled) systemEngine.vibrate(duration)
    }

    private fun playAudio(uri: String) {
        if (appSettings.muteAllSounds) return
        audioPlayer.playSound(uri)
    }

    private fun toggleKeepScreenOn(enabled: Boolean) {
        systemEngine.keepScreenOn(enabled)
    }

    override fun onCleared() {
        timerJob?.cancel()
        audioPlayer.release()
        toggleKeepScreenOn(false)
        super.onCleared()
    }
}