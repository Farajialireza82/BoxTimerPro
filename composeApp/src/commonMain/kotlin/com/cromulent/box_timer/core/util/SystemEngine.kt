package com.cromulent.box_timer.core.util

expect class SystemEngine {
    fun vibrate(duration: Long)
    fun openEmail()
    fun isBatteryOptimizationEnabled(): Boolean
    fun openOptimizationSettings()
}