package com.cromulent.box_timer.presentation.timer_screen

sealed interface TimerActions {
    object StartTimer : TimerActions
    object PauseTimer : TimerActions
    object ResetTimer : TimerActions
    object SkipTimer : TimerActions
    object CompleteWorkout : TimerActions
}