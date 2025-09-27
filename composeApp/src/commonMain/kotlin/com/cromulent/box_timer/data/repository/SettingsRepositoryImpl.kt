package com.cromulent.box_timer.data.repository

import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerSettings
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json

class SettingsRepositoryImpl(): SettingsRepository {

    val settings = Settings()

    override suspend fun updateTimerSettings(timerSettings: TimerSettings) {
        settings.putString("timerSettings", Json.encodeToString(timerSettings))
    }

    override suspend fun getTimerSettings(): TimerSettings? {
         val timerJson = settings.getStringOrNull("timerSettings")
        return if (timerJson != null) {
            Json.decodeFromString(timerJson)
        } else {
            null
        }
    }



}