package com.cromulent.box_timer.presentation.configuration_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerRepository
import com.cromulent.box_timer.domain.TimerSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    val systemEngine: SystemEngine,
    val timerRepository: TimerRepository
): ViewModel() {

    private val _timerSettings = MutableStateFlow(TimerSettings(
        roundDuration = 180000,
        restDuration = 60000,
        totalRounds = 3
    ))
    val timerSettings: StateFlow<TimerSettings> = _timerSettings.asStateFlow()

    private val _isBatteryOptimizationEnabled = MutableStateFlow(false)
    val isBatteryOptimizationEnabled = _isBatteryOptimizationEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            timerRepository.getTimerSettings().let {
                _timerSettings.value = it
            }
        }
        checkShouldShowBatteryOptimizationDialog()
    }



    fun onAction(action: ConfigurationActions){

        when(action){
            is ConfigurationActions.SaveTimerSettings -> saveTimerSettings(action.timerSettings)
            is ConfigurationActions.DismissBatteryOptimizationDialog -> dismissBatteryOptimizationDialog()
            is ConfigurationActions.OpenOptimizationSettings -> {
                systemEngine.openOptimizationSettings()
            }
        }

    }



    private fun checkShouldShowBatteryOptimizationDialog() {
        _isBatteryOptimizationEnabled.update {
            systemEngine.shouldShowBatteryOptimizationDialog()
        }
    }

    private fun dismissBatteryOptimizationDialog() {
        systemEngine.dismissBatteryDialog()
        _isBatteryOptimizationEnabled.update { false }
    }

    private fun saveTimerSettings(timerSettings: TimerSettings){
        viewModelScope.launch {
            timerRepository.updateTimerSettings(timerSettings)
        }
    }

}