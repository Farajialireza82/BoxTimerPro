package com.cromulent.box_timer.data.repository

import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.TimerRepository
import com.cromulent.box_timer.domain.TimerSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class TimerRepositoryImpl(
    private val settings: Settings
) : TimerRepository {

    private val observableSettings = settings as ObservableSettings

    @OptIn(ExperimentalSettingsApi::class)
    override val timerSettings: Flow<TimerSettings>  = observableSettings.getStringFlow("timer_settings", "NULL").map { settings ->
        if (settings == "NULL") {
            TimerSettings()
        } else {
            Json.decodeFromString(settings)
        }
    }

    override suspend fun updateTimerSettings(timerSettings: TimerSettings) {
        observableSettings.putString("timer_settings", Json.encodeToString(timerSettings))
    }

    override suspend fun getTimerSettings(): TimerSettings {
        val timerJson = observableSettings.getStringOrNull("timer_settings")
        return if (timerJson != null) {
            Json.decodeFromString(timerJson)
        } else {
            TimerSettings()
        }
    }


}