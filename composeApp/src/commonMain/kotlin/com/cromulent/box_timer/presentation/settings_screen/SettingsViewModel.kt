package com.cromulent.box_timer.presentation.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.core.util.AudioFile
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.LanguageManager
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.domain.AppLanguage
import com.cromulent.box_timer.domain.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    val settingsRepository: SettingsRepository,
    val audioPlayer: AudioPlayer,
    val systemEngine: SystemEngine,
    val languageManager: LanguageManager,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())

    init {
        viewModelScope.launch {
            settingsRepository.appSettings.collect { appSettings ->
                    _state.update {
                        it.copy(
                            appSettings = appSettings
                        )
                    }
            }
        }
    }

    val state = _state.asStateFlow()

    val settings = settingsRepository.appSettings

    fun onAction(action: SettingsActions) {

        when (action) {
            is SettingsActions.OnAppVersionClick -> {}
            is SettingsActions.OnFeedbackClick -> onFeedbackClicked()
            is SettingsActions.SetCountdownSound -> setCountDownSound(action.audioFile)
            is SettingsActions.SetEndRoundSound -> setEndRoundSound(action.audioFile)
            is SettingsActions.SetStartRoundSound -> setStartRoundSound(action.audioFile)
            is SettingsActions.ToggleAllowRotation -> toggleAllowRotation(action.isEnabled)
            is SettingsActions.ToggleKeepScreenOn -> toggleKeepScreenOn(action.isEnabled)
            is SettingsActions.ToggleMuteAllSounds -> toggleMuteAllSounds(action.isEnabled)
            is SettingsActions.ToggleVibrationHaptic -> toggleVibrationHaptic(action.isEnabled)
            is SettingsActions.ToggleStopTimerOnClose -> toggleStopTimerOnClose(action.isEnabled)
            is SettingsActions.PlayAudio -> playAudio(action.uri)
            is SettingsActions.SetColorScheme -> setColorScheme(action.colorSchemeId)
            is SettingsActions.ToggleDarkMode -> toggleDarkMode()
            is SettingsActions.SetLanguage -> setLanguage(action.language)
        }

    }

    private fun toggleStopTimerOnClose(isEnabled: Boolean){
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    stopTimerOnClose = isEnabled
                )
            )
        }
    }

    private fun toggleDarkMode(){
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    isDarkMode = !_state.value.appSettings.isDarkMode
                )
            )
        }
    }

    private fun playAudio(uri: String?) {
        audioPlayer.playSound(uri)
    }

    private fun onFeedbackClicked() {
        systemEngine.openEmail()
    }

    private fun setColorScheme(colorSchemeId: String) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    colorSchemeId = colorSchemeId
                )
            )
        }
    }

    private fun setCountDownSound(audioFile: AudioFile) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    countDownAudioFile = audioFile
                )
            )
        }
    }

    private fun setEndRoundSound(audioFile: AudioFile) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    endRoundAudioFile = audioFile
                )
            )
        }
    }

    private fun setStartRoundSound(audioFile: AudioFile) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    startRoundAudioFile = audioFile
                )
            )
        }
    }

    private fun toggleAllowRotation(isEnabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    allowRotation = isEnabled
                )
            )
        }
    }

    private fun toggleKeepScreenOn(isEnabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    keepScreenOnEnabled = isEnabled
                )
            )
        }
    }

    private fun toggleMuteAllSounds(isEnabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    muteAllSounds = isEnabled
                )
            )
        }
    }

    private fun toggleVibrationHaptic(isEnabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    isVibrationEnabled = isEnabled
                )
            )
        }
    }

    private fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            // Update the language manager first to apply the change immediately
            languageManager.setLanguage(language)
            
            // Then update the settings to persist the change
            settingsRepository.updateAppSettings(
                _state.value.appSettings.copy(
                    selectedLanguage = language
                )
            )
        }
    }

}