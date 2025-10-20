package com.cromulent.box_timer

import android.app.Application
import com.cromulent.box_timer.core.di.initKoin
import com.cromulent.box_timer.data.AppContainer
import com.cromulent.box_timer.data.DefaultAppContainer
import org.koin.android.ext.koin.androidContext

class BoxTimerPro: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        container = DefaultAppContainer()
        super.onCreate()
        initKoin {
            androidContext(this@BoxTimerPro)
        }
    }

}