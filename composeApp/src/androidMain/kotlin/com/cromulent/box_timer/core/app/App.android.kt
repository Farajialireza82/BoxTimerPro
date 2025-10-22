package com.cromulent.box_timer.core.app

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.cromulent.box_timer.core.service.TimerService
import com.cromulent.box_timer.data.AppContainer
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

@Composable
@Preview
actual fun App() {

    val context = LocalContext.current
    val appContainer = koinInject<AppContainer>()
    val timerState by appContainer.timerState.collectAsState()

    val navController = rememberNavController()
    BoxTimerProTheme(timerState = timerState) {

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

                    composable<Route.ConfigurationScreen>{
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

                        val viewModel = koinViewModel<TimerViewModel>()

                        TimerScreenRoot(
                            viewModel = viewModel,
                            state = timerState,
                            closeTimerScreen = {
                                navController.popBackStack()
                                Intent(context, TimerService::class.java).also {
                                    it.action = TimerService.Actions.RESET.toString()
                                    context.startService(it)
                                }
                                context.stopService(Intent(context, TimerService::class.java))
                            },
                            modifier = Modifier
                        )

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
    val currentDestination = navController.currentBackStackEntry?.destination
    if (TimerService.isRunning && currentDestination?.route != Route.TimerScreen::class.qualifiedName) {
        navController.navigate(Route.TimerScreen)
    }


}