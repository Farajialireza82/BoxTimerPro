package com.cromulent.box_timer.core.app

import kotlinx.serialization.Serializable

sealed interface Route {


    @Serializable
    data object TimerGraph: Route


    @Serializable
    data object ConfigurationScreen: Route


    @Serializable
    data object TimerScreen: Route

    @Serializable
    data object SettingsScreen: Route


}