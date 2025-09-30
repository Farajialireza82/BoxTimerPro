package com.cromulent.box_timer.core.util

import platform.UIKit.UIApplication
import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle

actual class SystemEngine {
    actual fun vibrate(duration: Long) {
        val generator = UIImpactFeedbackGenerator(
            style = if(duration < 600L) UIImpactFeedbackStyle.UIImpactFeedbackStyleLight
            else if(duration < 1000L) UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium
            else UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy
        )
        generator.prepare()
        generator.impactOccurred()

    }

    actual fun keepScreenOn(keepScreenOn: Boolean) {
        UIApplication.sharedApplication.idleTimerDisabled = keepScreenOn
    }
}