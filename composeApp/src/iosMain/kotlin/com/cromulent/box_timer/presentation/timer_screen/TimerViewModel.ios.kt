package com.cromulent.box_timer.presentation.timer_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerRepository
import com.cromulent.box_timer.domain.TimerSettings
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

actual class TimerViewModel(
    settingsRepository: SettingsRepository,
    timerRepository: TimerRepository,
    val audioPlayer: AudioPlayer,
    val systemEngine: SystemEngine
) : ViewModel(){


    private val _state = MutableStateFlow(TimerState())
    actual val state = _state.asStateFlow()

    lateinit var appSettings: AppSettings
    lateinit var timerSettings: TimerSettings
    private var isResting = false


    init {
        viewModelScope.launch {
            appSettings = settingsRepository.getAppSettings()
            timerSettings = timerRepository.getTimerSettings()
            delay(300)
            start()
        }
    }

    private var timerJob: Job? = null

    actual fun onAction(action: TimerActions) {
        when (action) {
            TimerActions.StartTimer -> start()
            TimerActions.PauseTimer -> pause()
            TimerActions.ResetTimer -> reset()
            TimerActions.CompleteWorkout -> reset()
        }
    }

    private fun start() {
        if (_state.value.timerStatus.isInActiveState()) return
        if (_state.value.timerStatus != TimerStatus.Paused) {
            _state.update {
                it.copy(
                    totalRounds = timerSettings.totalRounds,
                    remainingTime = timerSettings.roundDuration,
                    progress = 0f
                )
            }
        }
        _state.update { it.copy(timerStatus = TimerStatus.Running) }
        toggleKeepScreenOn(appSettings.keepScreenOnEnabled)


        timerJob = viewModelScope.launch {

            while (_state.value.currentRound <= _state.value.totalRounds && _state.value.timerStatus.isInActiveState()) {

                if (_state.value.currentRound == 1 && _state.value.remainingTime == timerSettings.roundDuration) {
                    runCountDown()
                }


                if (isResting.not()) {
                    if (_state.value.remainingTime ==  timerSettings.roundDuration) startRoundAlert()
                    runTimer(
                        duration = _state.value.remainingTime,
                        delay = 10L,
                        callBack = { currentTime, remainingTime ->
                            _state.update {
                                it.copy(
                                    remainingTime = remainingTime,
                                    progress = 1f - _state.value.remainingTime /  timerSettings.roundDuration.toFloat()
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
                    _state.update { it.copy(timerStatus = TimerStatus.Resting, remainingTime = if(isResting) it.remainingTime else  timerSettings.restDuration) }
                    isResting = true
                    runTimer(
                        duration = _state.value.remainingTime,
                        delay = 10L,
                        callBack = { currentTime, remainingTime ->
                            _state.update {
                                it.copy(
                                    remainingTime = remainingTime,
                                    progress = 1f - (_state.value.remainingTime.toFloat() /  timerSettings.restDuration.toFloat())
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
                            remainingTime =  timerSettings.roundDuration,
                            progress = 0f
                        )
                    }
                } else {
                    // All rounds completed - show completion status
                    _state.update { it.copy(timerStatus = TimerStatus.Completed) }
                    endWorkoutAlert()
                    break
                }
            }


        }
    }

    private fun pause() {
        toggleKeepScreenOn(false)
        timerJob?.cancel()
        timerJob = null
        _state.update { it.copy(timerStatus = TimerStatus.Paused) }
    }

    private fun reset() {
        toggleKeepScreenOn(false)
        timerJob?.cancel()
        timerJob = null
        _state.update {
            it.copy(
                timerStatus = TimerStatus.Ready,
                currentRound = 1,
                progress = 0f,
                totalRounds = timerSettings.totalRounds,
                remainingTime =  timerSettings.roundDuration,
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

    private fun endWorkoutAlert() {
        vibratePhone(2000L)
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

    private fun playAudio(uri: String?) {
        if (appSettings.muteAllSounds) return
        audioPlayer.playSound(uri)
    }

    private fun toggleKeepScreenOn(enabled: Boolean) {
        systemEngine.keepScreenOn(enabled)
    }

    private suspend fun runCountDown() {
        val beforeStatus = state.value.timerStatus
        for (i in 1..3) {
            _state.update {
                it.copy(
                    timerStatus = TimerStatus.CountDown,
                    countDownText = "Get Ready: ${4 - i}", // TODO: This should use string resources but we're in a service context
                )
            }
            countDownAlert()
            delay(1000L)
        }
        _state.update {
            it.copy(
                timerStatus = beforeStatus,
                countDownText = ""
            )
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        audioPlayer.release()
        toggleKeepScreenOn(false)
        super.onCleared()
    }
}