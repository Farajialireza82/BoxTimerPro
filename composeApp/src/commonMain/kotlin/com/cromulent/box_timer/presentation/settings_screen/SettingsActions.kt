package com.cromulent.box_timer.presentation.settings_screen

import androidx.compose.material3.ColorScheme
import com.cromulent.box_timer.core.util.AudioFile
import com.cromulent.box_timer.domain.AppLanguage

sealed interface SettingsActions {

    data class ToggleMuteAllSounds(val isEnabled: Boolean): SettingsActions
    data class SetCountdownSound(val audioFile: AudioFile): SettingsActions
    data class SetStartRoundSound(val audioFile: AudioFile): SettingsActions
    data class SetEndRoundSound(val audioFile: AudioFile): SettingsActions
    data class ToggleVibrationHaptic(val isEnabled: Boolean): SettingsActions
    data class ToggleKeepScreenOn(val isEnabled: Boolean): SettingsActions
    data class ToggleAllowRotation(val isEnabled: Boolean): SettingsActions
    data class ToggleStopTimerOnClose(val isEnabled: Boolean): SettingsActions
    data class PlayAudio(val uri: String?): SettingsActions
    data class SetColorScheme(val colorSchemeId: String): SettingsActions
    data class SetLanguage(val language: AppLanguage): SettingsActions

    object OnAppVersionClick: SettingsActions
    object OnFeedbackClick: SettingsActions
    object ToggleDarkMode: SettingsActions
    object CheckBatteryOptimization: SettingsActions
    object OpenOptimizationSettings: SettingsActions

}