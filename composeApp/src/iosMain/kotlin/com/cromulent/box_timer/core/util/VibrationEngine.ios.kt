package com.cromulent.box_timer.core.util

import platform.AudioToolbox.AUDIO_TOOLBOX_VERSION
import platform.AudioToolbox.AudioServicesPlayAlertSound
import platform.AudioToolbox.AudioServicesPlayAlertSoundWithCompletion
import platform.AudioToolbox.SystemSoundID
import platform.AudioToolbox.kSystemSoundID_Vibrate
import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle

actual class VibrationEngine {
    actual fun vibrate(duration: Long) {
        val generator = UIImpactFeedbackGenerator(
            style = if(duration < 600L) UIImpactFeedbackStyle.UIImpactFeedbackStyleLight
            else if(duration < 1000L) UIImpactFeedbackStyle.UIImpactFeedbackStyleMedium
            else UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy
        )
        generator.prepare()
        generator.impactOccurred()

    }
}