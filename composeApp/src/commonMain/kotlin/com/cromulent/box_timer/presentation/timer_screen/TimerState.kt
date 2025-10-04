package com.cromulent.box_timer.presentation.timer_screen


data class TimerState(
    val isTimerRunning: Boolean = false,
    val isPaused: Boolean = false,
    val remainingTime: Long = 0L,
    val progress: Float = 0f,
    val currentRound: Int = 1,
    val totalRounds: Int = 12,
    val roundDuration: Long = 180_000L, // 3 min
    val restDuration: Long = 30_000L,
    val timerMessage: String = "Ready",
)