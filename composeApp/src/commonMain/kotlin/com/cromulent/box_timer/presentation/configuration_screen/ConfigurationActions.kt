package com.cromulent.box_timer.presentation.configuration_screen

import com.cromulent.box_timer.domain.TimerSettings

sealed interface ConfigurationActions {


    data class SaveTimerSettings(val timerSettings: TimerSettings): ConfigurationActions
    data object DismissBatteryOptimizationDialog: ConfigurationActions
    object OpenOptimizationSettings: ConfigurationActions


}