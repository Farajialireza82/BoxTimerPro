package com.cromulent.box_timer.core.di

import android.app.Activity
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.data.AppContainer
import com.cromulent.box_timer.data.DefaultAppContainer
import com.cromulent.box_timer.presentation.timer_screen.TimerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val platformModule: Module = module {

    single { AudioPlayer(androidApplication()) }
    single { SystemEngine(get<Activity>()) }
    viewModel { 
        TimerViewModel(
            settingsRepository = get(),
            audioPlayer = get(),
            systemEngine = get(),
            context = androidApplication()
        )
    }
    single<AppContainer> { DefaultAppContainer() }

}