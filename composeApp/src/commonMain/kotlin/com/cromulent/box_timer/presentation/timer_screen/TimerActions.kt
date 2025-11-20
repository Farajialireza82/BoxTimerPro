package com.cromulent.box_timer.presentation.timer_screen

import com.cromulent.box_timer.domain.timer.Lap

sealed interface TimerActions {
    object StartTimer : TimerActions
    object PauseTimer : TimerActions
    object ResetTimer : TimerActions
    object SkipTimer : TimerActions
    object LapTimer : TimerActions
    object CompleteWorkout : TimerActions
    data class DeleteLap(val lap: Lap): TimerActions
}