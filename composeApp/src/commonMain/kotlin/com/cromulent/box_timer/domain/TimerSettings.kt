package com.cromulent.box_timer.domain

import kotlinx.serialization.Serializable

@Serializable
data class TimerSettings(
    val roundDuration: Long = 180000,
    val restDuration: Long = 60000,
    val totalRounds: Int = 3,
    val timerFrequency: Float = 10f,
)
