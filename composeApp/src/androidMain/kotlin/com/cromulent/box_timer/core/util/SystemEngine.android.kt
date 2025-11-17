package com.cromulent.box_timer.core.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.core.net.toUri
import java.util.concurrent.TimeUnit
import androidx.core.content.edit


actual class SystemEngine(val activity: Activity) {

    companion object {
        private const val KEY_LAST_SHOWN = "battery_dialog_last_shown"
        private val ONE_DAY_MILLIS = TimeUnit.DAYS.toMillis(1)
    }

    private var preferences: SharedPreferences = activity.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)


    private var vibrator: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            activity.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        activity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    actual fun vibrate(duration: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val vibrationEffect =
                VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)

            vibrator.vibrate(vibrationEffect)
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(duration)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    actual fun openEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
            .setData("mailto:farajialireza001@gmail.com".toUri())
            activity.startActivity(intent)
    }

    actual fun isBatteryOptimizationEnabled(): Boolean{
        val powerManager = activity.getSystemService(Context.POWER_SERVICE) as PowerManager
        return powerManager.isIgnoringBatteryOptimizations(activity.packageName).not()
    }

    actual fun shouldShowBatteryOptimizationDialog(): Boolean{
        val lastShown = preferences.getLong(KEY_LAST_SHOWN, 0L)
        val now = System.currentTimeMillis()
        return (now - lastShown) >= ONE_DAY_MILLIS && isBatteryOptimizationEnabled()
    }

    actual fun dismissBatteryDialog() {
        preferences.edit {
            putLong(KEY_LAST_SHOWN, System.currentTimeMillis())
        }
    }

    @SuppressLint("BatteryLife")
    actual fun openOptimizationSettings(){
        try {
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = "package:${activity.packageName}".toUri()
            activity.startActivity(intent)
        } catch (e: Exception) {
            Log.e("BatteryOpt", "Failed to open battery settings", e)
            openGeneralBatterySettings()
        }
    }

    private fun openGeneralBatterySettings() {
        try {
            val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            activity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}