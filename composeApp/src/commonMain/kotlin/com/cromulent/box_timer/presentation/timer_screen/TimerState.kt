package com.cromulent.box_timer.presentation.timer_screen


data class TimerState(
    val isTimerRunning: Boolean = false,
    val currentTime: Long = 0L,
    val currentRound: Int = 1,
    val totalRounds: Int = 12,
    val roundDuration: Long = 180_000L, // 3 min
    val restDuration: Long = 30_000L,
    val timerMessage: String = "Ready",
    val phase: TimerPhase = TimerPhase.IDLE
)

enum class TimerPhase { IDLE, FIGHT, REST, COUNTDOWN }