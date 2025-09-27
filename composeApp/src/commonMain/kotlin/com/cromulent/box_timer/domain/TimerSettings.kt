package com.cromulent.box_timer.domain

import com.cromulent.box_timer.presentation.timer_screen.TimerState
import kotlinx.serialization.Serializable

@Serializable
data class TimerSettings(
    val roundDuration: Long,
    val restDuration: Long,
    val totalRounds: Int,
){

    fun toTimerState() = TimerState(
        roundDuration = roundDuration,
        restDuration = restDuration,
        totalRounds = totalRounds
    )

}
