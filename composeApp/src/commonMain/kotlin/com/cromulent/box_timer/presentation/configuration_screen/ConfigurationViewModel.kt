package com.cromulent.box_timer.presentation.configuration_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerRepository
import com.cromulent.box_timer.domain.TimerSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConfigurationViewModel(
    val timerRepository: TimerRepository
): ViewModel() {

    private val _timerSettings = MutableStateFlow(TimerSettings(
        roundDuration = 180000,
        restDuration = 60000,
        totalRounds = 3
    ))
    val timerSettings: StateFlow<TimerSettings> = _timerSettings.asStateFlow()

    init {
        viewModelScope.launch {
            timerRepository.getTimerSettings()?.let {
                _timerSettings.value = it
            }
        }
    }



    fun onAction(action: ConfigurationActions){

        when(action){
            is ConfigurationActions.SaveTimerSettings -> saveTimerSettings(action.timerSettings)
        }

    }

    private fun saveTimerSettings(timerSettings: TimerSettings){
        viewModelScope.launch {
            timerRepository.updateTimerSettings(timerSettings)
        }
    }

}