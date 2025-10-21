package com.cromulent.box_timer.domain

import kotlinx.serialization.Serializable

@Serializable
data class TimerSettings(
    val roundDuration: Long = 10_000L,
    val restDuration: Long = 5_000L,
    val totalRounds: Int = 3,
    val timerFrequency: Float = 10f,
)
