package com.cromulent.box_timer.core.di

import android.app.Activity
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.LanguageManager
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.data.AppContainer
import com.cromulent.box_timer.data.DefaultAppContainer
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import com.cromulent.box_timer.presentation.timer_screen.TimerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {

    singleOf(::AudioPlayer).bind<AudioPlayer>()
    singleOf(::SystemEngine).bind<SystemEngine>()
    singleOf(::LanguageManager).bind<LanguageManager>()
    singleOf(::DefaultAppContainer).bind<AppContainer>()

    viewModel { 
        TimerViewModel(
            settingsRepository = get(),
            audioPlayer = get(),
            systemEngine = get(),
            context = androidApplication()
        )
    }
    
    viewModel { 
        SettingsViewModel(
            settingsRepository = get(),
            audioPlayer = get(),
            systemEngine = get(),
            languageManager = get()
        )
    }
    
}