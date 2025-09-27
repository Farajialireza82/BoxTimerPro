package com.cromulent.box_timer.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.data.repository.SettingsRepositoryImpl
import com.cromulent.box_timer.presentation.TimerSettingsViewModel
import com.cromulent.box_timer.presentation.configuration_screen.ConfigurationScreen
import com.cromulent.box_timer.presentation.timer_screen.TimerScreenRoot
import com.cromulent.box_timer.presentation.timer_screen.TimerViewmodel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val timerSettingsViewModel = TimerSettingsViewModel(SettingsRepositoryImpl())

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
                        ConfigurationScreen(
                            timerSettingsViewModel.timerSettings.value
                        ) { timerConfigs ->
                            timerSettingsViewModel.onSettingsSaved(timerConfigs)
                            navController.navigate(Route.TimerScreen)
                        }

                    }

                    composable<Route.TimerScreen>(

                    ) {
                        val timerConfigs by timerSettingsViewModel.timerSettings.collectAsState()
                        val viewmodel = TimerViewmodel(timerConfigs)
                        TimerScreenRoot(viewmodel)

                    }


                }
            }

        }

    }
}