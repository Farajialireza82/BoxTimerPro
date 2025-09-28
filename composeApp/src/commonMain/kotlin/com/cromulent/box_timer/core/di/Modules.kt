package com.cromulent.box_timer.core.di

import com.cromulent.box_timer.data.repository.SettingsRepositoryImpl
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationViewModel
import com.cromulent.box_timer.presentation.timer_screen.TimerViewmodel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    singleOf(::SettingsRepositoryImpl).bind<SettingsRepository>()

    viewModelOf(::ConfigurationViewModel)
    viewModelOf(::TimerViewmodel)

}

