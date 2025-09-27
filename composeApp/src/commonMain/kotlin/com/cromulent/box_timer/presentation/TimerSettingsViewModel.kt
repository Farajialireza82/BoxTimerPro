package com.cromulent.box_timer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerSettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _timerSettings = MutableStateFlow(TimerSettings(
        roundDuration = 180000,
        restDuration = 60000,
        totalRounds = 12
    ))
    val timerSettings: StateFlow<TimerSettings> = _timerSettings.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.getTimerSettings()?.let {
                _timerSettings.value = it
            }
        }
    }

    fun onSettingsSaved(timerSettings: TimerSettings) {
        _timerSettings.value = timerSettings
        viewModelScope.launch {
            settingsRepository.updateTimerSettings(timerSettings)
        }
    }

}