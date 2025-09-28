package com.cromulent.box_timer

import androidx.compose.ui.window.ComposeUIViewController
import com.cromulent.box_timer.core.app.App
import com.cromulent.box_timer.core.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) { App() }