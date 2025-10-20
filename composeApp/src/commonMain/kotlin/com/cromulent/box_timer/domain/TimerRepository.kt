package com.cromulent.box_timer.domain

import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    suspend fun updateTimerSettings(timerSettings: TimerSettings)
    suspend fun getTimerSettings(): TimerSettings
    val timerSettings: Flow<TimerSettings>
}