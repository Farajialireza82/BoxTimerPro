package com.cromulent.box_timer.presentation.settings_screen

import com.cromulent.box_timer.core.util.AudioFile

sealed interface SettingsActions {

    data class ToggleMuteAllSounds(val isEnabled: Boolean): SettingsActions
    data class SetCountdownSound(val audioFile: AudioFile): SettingsActions
    data class SetStartRoundSound(val audioFile: AudioFile): SettingsActions
    data class SetEndRoundSound(val audioFile: AudioFile): SettingsActions
    data class ToggleVibrationHaptic(val isEnabled: Boolean): SettingsActions
    data class ToggleKeepScreenOn(val isEnabled: Boolean): SettingsActions
    data class ToggleAllowRotation(val isEnabled: Boolean): SettingsActions
    data class PlayAudio(val uri: String): SettingsActions

    object OnAppVersionClick: SettingsActions
    object OnFeedbackClick: SettingsActions

}