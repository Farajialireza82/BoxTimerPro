package com.cromulent.box_timer.domain

interface SettingsRepository {

    suspend fun updateTimerSettings(timerSettings: TimerSettings)

    suspend fun getTimerSettings(): TimerSettings?

}