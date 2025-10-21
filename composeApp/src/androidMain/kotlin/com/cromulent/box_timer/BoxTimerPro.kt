package com.cromulent.box_timer

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.cromulent.box_timer.core.di.initKoin
import com.cromulent.box_timer.data.AppContainer
import com.cromulent.box_timer.data.DefaultAppContainer
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.compose.koinInject

class BoxTimerPro: Application() {
    lateinit var container: AppContainer
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BoxTimerPro)
        }
        container = DefaultAppContainer(applicationContext)

        val notificationChannel = NotificationChannel(
            "timer",
            "TimerProgress",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        container.notificationManager.createNotificationChannel(notificationChannel)
    }

}