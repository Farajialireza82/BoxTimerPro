package com.cromulent.box_timer.core.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.cromulent.box_timer.core.changelog.ChangelogManager
import com.cromulent.box_timer.core.util.LanguageManager
import com.cromulent.box_timer.data.repository.SettingsRepositoryImpl
import com.cromulent.box_timer.data.repository.TimerRepositoryImpl
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerRepository
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationViewModel
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import com.cromulent.box_timer.presentation.timer_screen.TimerViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    val settings = Settings()
    
    single { settings }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<TimerRepository> { TimerRepositoryImpl(get()) }
    single { ChangelogManager(settings) }

    viewModelOf(::ConfigurationViewModel)
    viewModelOf(::SettingsViewModel)

}

