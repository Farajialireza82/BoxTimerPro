package com.cromulent.box_timer.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun updateAppSettings(appSettings: AppSettings)
    suspend fun getAppSettings(): AppSettings?

    val appSettings: Flow<AppSettings?>
}