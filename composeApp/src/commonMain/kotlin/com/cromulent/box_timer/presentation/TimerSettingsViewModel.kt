package com.cromulent.box_timer.presentation

import androidx.lifecycle.ViewModel
import com.cromulent.box_timer.domain.TimerConfigurations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerSettingsViewModel: ViewModel() {

    private val _timerConfigs = MutableStateFlow(TimerConfigurations(
        roundDuration = 180000,
        restDuration = 60000,
        totalRounds = 12
    ))
    val timerConfigs: StateFlow<TimerConfigurations> = _timerConfigs.asStateFlow()

    fun onSettingsSaved(timerConfigs: TimerConfigurations) {
        _timerConfigs.value = timerConfigs
    }

}