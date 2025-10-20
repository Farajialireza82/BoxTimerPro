package com.cromulent.box_timer.data.repository

import androidx.compose.runtime.collectAsState
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class SettingsRepositoryImpl() : SettingsRepository {

    val settings = Settings() as ObservableSettings

    @OptIn(ExperimentalSettingsApi::class)
    override val appSettings: Flow<AppSettings?>  = settings.getStringFlow("app_settings", "NULL").map { settings ->
        if (settings == "NULL") {
            null
        } else {
            Json.decodeFromString(settings)
        }
    }

    override suspend fun updateAppSettings(appSettings: AppSettings) {
        settings.putString("app_settings", Json.encodeToString(appSettings))
    }

    override suspend fun getAppSettings(): AppSettings? {
        val appStringJson = settings.getStringOrNull("app_settings")
        return if (appStringJson != null) {
            Json.decodeFromString(appStringJson)
        } else null
    }

}