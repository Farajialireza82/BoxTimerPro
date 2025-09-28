package com.cromulent.box_timer.core.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationScreenRoot
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationViewModel
import com.cromulent.box_timer.presentation.timer_screen.TimerScreenRoot
import com.cromulent.box_timer.presentation.timer_screen.TimerViewmodel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {

        Box(
            modifier = Modifier
                .background(backgroundGradientBrush)
                .fillMaxSize()
        ) {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.TimerGraph
            ) {

                navigation<Route.TimerGraph>(
                    startDestination = Route.ConfigurationScreen
                ) {

                    composable<Route.ConfigurationScreen>(
                    ) {
                        val viewmodel = koinViewModel<ConfigurationViewModel>()
                        ConfigurationScreenRoot(
                            viewModel = viewmodel,
                            navigateToTimerScreen = {
                                navController.navigate(Route.TimerScreen)
                            })
                    }

                    composable<Route.TimerScreen>(

                    ) {
                        val viewmodel = koinViewModel<TimerViewmodel>()
                        TimerScreenRoot(viewmodel)

                    }


                }
            }

        }

    }
}