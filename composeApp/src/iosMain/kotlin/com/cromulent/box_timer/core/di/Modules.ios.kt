package com.cromulent.box_timer.core.di

import com.cromulent.box_timer.core.util.AudioPlayer
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {

    single { AudioPlayer() }

}