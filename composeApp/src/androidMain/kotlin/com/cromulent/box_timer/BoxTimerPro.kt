package com.cromulent.box_timer

import android.app.Application
import com.cromulent.box_timer.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class BoxTimerPro: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BoxTimerPro)
        }
    }

}