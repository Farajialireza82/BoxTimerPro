package com.cromulent.box_timer.presentation

import androidx.lifecycle.ViewModel
import com.cromulent.box_timer.domain.TimerSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerSettingsViewModel: ViewModel() {

    private val _timerConfigs = MutableStateFlow(TimerSettings(
        roundDuration = 180000,
        restDuration = 60000,
        totalRounds = 12
    ))
    val timerConfigs: StateFlow<TimerSettings> = _timerConfigs.asStateFlow()

    fun onSettingsSaved(timerConfigs: TimerSettings) {
        _timerConfigs.value = timerConfigs
    }

}