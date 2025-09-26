package com.cromulent.box_timer.presentation.timer_screen

data class TimerState(
    val isTimerRunning: Boolean = false,
    val currentTimeMillis: Long = 0L,
    val totalTimeMillis: Long = 180000,
    val currentRound: Int = 1,
    val totalRounds: Int = 12
)
