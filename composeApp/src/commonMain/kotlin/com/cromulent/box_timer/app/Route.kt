package com.cromulent.box_timer.app

import kotlinx.serialization.Serializable

sealed interface Route {


    @Serializable
    data object TimerGraph: Route


    @Serializable
    data object ConfigurationScreen: Route


    @Serializable
    data object TimerScreen: Route


}