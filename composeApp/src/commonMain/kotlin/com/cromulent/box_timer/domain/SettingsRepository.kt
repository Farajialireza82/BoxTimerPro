package com.cromulent.box_timer.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun updateAppSettings(appSettings: AppSettings)
    suspend fun updateTimerSettings(timerSettings: TimerSettings)
    suspend fun getAppSettings(): AppSettings?
    suspend fun getTimerSettings(): TimerSettings?

    val appSettings: Flow<AppSettings?>
}