package com.cromulent.box_timer.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationScreenRoot
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationViewModel
import com.cromulent.box_timer.presentation.settings_screen.SettingsScreenRoot
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.presentation.timer_screen.TimerScreenRoot
import com.cromulent.box_timer.presentation.timer_screen.TimerViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import platform.UIKit.UIApplication

@Composable
@Preview
actual fun App() {

    val navController = rememberNavController()
    val timerViewModel = koinViewModel<TimerViewModel>()
    val timerState by timerViewModel.state.collectAsStateWithLifecycle()
    val settingsRepo = koinInject<SettingsRepository>()
    val settings by settingsRepo.appSettings.collectAsState(null)

    BoxTimerProTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            NavHost(
                navController = navController,
                startDestination = Route.TimerGraph
            ) {

                navigation<Route.TimerGraph>(
                    startDestination = Route.ConfigurationScreen
                ) {

                    composable<Route.ConfigurationScreen>(
                    ) {
                        val viewModel = koinViewModel<ConfigurationViewModel>()
                        ConfigurationScreenRoot(
                            viewModel = viewModel,
                            navigateToTimerScreen = {
                                navController.navigate(Route.TimerScreen)
                            },
                            navigateToSettings = {
                                navController.navigate(Route.SettingsScreen)
                            }
                        )
                    }

                    composable<Route.TimerScreen> {


                        DisposableEffect(settings) {
                            if (settings?.keepScreenOnEnabled == true) {
                                UIApplication.sharedApplication.idleTimerDisabled = true
                            }

                            onDispose {
                                UIApplication.sharedApplication.idleTimerDisabled = false
                            }
                        }

                        TimerScreenRoot(
                            viewModel = timerViewModel,
                            state = timerState,
                            closeTimerScreen = {
                                navController.popBackStack()
                            },
                            modifier = Modifier)

                    }

                    composable<Route.SettingsScreen> {
                        val viewModel = koinViewModel<SettingsViewModel>()
                        SettingsScreenRoot(
                            viewModel = viewModel,
                            onBackButtonClicked = {
                                navController.popBackStack()
                            })
                    }


                }
            }
        }
    }
}