package com.cromulent.box_timer.presentation.timer_screen

import com.cromulent.box_timer.domain.TimerSettings

data class TimerState(
    val isTimerRunning: Boolean = false,
    val currentTime: Long = 0L,
    val currentRound: Int = 1,
    val totalRounds: Int,
    val roundDuration: Long,
    val restDuration: Long,
){

    fun toTimerSettings() = TimerSettings(
        roundDuration = roundDuration,
        restDuration = restDuration,
        totalRounds = totalRounds
    )

}
