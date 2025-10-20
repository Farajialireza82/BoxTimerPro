package com.cromulent.box_timer.core.di

import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.presentation.timer_screen.TimerViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val platformModule: Module = module {

    single { AudioPlayer() }
    single { SystemEngine() }
    viewModelOf(::TimerViewModel)

}