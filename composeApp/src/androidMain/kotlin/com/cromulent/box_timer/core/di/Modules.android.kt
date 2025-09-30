package com.cromulent.box_timer.core.di

import android.app.Activity
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {

    single {
        AudioPlayer(androidApplication())
    }

    single {
        SystemEngine(get<Activity>())
    }

}