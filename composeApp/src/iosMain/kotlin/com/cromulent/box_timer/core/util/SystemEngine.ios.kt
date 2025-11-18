package com.cromulent.box_timer.core.util

import platform.Foundation.NSURL
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
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

    actual fun isBatteryOptimizationEnabled() = false

    actual fun openOptimizationSettings(){ }

    actual fun openEmail(){
        val urlString = "mailto:farajialireza001@gmail.com"
        val url = NSURL.URLWithString(urlString)
        if (url != null) {
            UIApplication.sharedApplication.openURL(url, emptyMap<Any?, Any?>()) { success ->
                if (!success) {
                    showAlert("Mail app is not configured on this device")
                }
            }
        }
    }

    private fun showAlert(message: String) {
        val alert = UIAlertController.alertControllerWithTitle(
            title = "Error",
            message = message,
            preferredStyle = UIAlertControllerStyleAlert
        )

        alert.addAction(
            UIAlertAction.actionWithTitle(
                title = "OK",
                style = UIAlertActionStyleDefault,
                handler = null
            )
        )

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        rootViewController?.presentViewController(alert, animated = true, completion = null)
    }

    //no iOS implementation
    actual fun shouldShowBatteryOptimizationDialog() = false

    //no iOS implementation
    actual fun dismissBatteryDialog() = Unit
}