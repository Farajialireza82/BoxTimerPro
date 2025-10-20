package com.cromulent.box_timer.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationScreenRoot
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationViewModel
import com.cromulent.box_timer.presentation.settings_screen.SettingsScreenRoot
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import com.cromulent.box_timer.presentation.timer_screen.TimerScreenRoot
import com.cromulent.box_timer.presentation.timer_screen.TimerViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
expect fun App()