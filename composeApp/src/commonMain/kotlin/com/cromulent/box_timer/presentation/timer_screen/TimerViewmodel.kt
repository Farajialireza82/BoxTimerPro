package com.cromulent.box_timer.presentation.timer_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.domain.TimerConfigurations
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimerViewmodel(
    timerConfigurations: TimerConfigurations
) : ViewModel() {

    private var _state = MutableStateFlow(TimerState(
        roundDuration = timerConfigurations.roundDuration,
        restDuration = timerConfigurations.restDuration,
        totalRounds = timerConfigurations.totalRounds
    ))
    val state = _state.asStateFlow()

    private var timerJob: Job? = null


    fun onAction(action: TimerActions) {
        when (action) {
            TimerActions.PauseTimer -> {
                pauseTimer()
            }

            TimerActions.ResetTimer -> {
                resetTimer()
            }

            TimerActions.StartTimer -> {
                startTimer()
            }
        }
    }

    private fun pauseTimer() {
        timerJob?.cancel()
        _state.update {
            it.copy(
                isTimerRunning = false
            )
        }
    }

    private fun resetTimer() {
        timerJob?.cancel()
        _state.update {
            it.copy(
                isTimerRunning = false,
                currentTime = 0L
            )
        }

    }

    private fun startTimer() {
        _state.update {
            it.copy(
                isTimerRunning = true
            )
        }

        timerJob = viewModelScope.launch {
            while (_state.value.isTimerRunning) {
                delay(10L)
                _state.update {
                    it.copy(
                        currentTime = it.currentTime + 10L
                    )
                }
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        timerJob = null

    }

}