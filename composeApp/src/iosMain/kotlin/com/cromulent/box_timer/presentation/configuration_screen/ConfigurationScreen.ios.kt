package com.cromulent.box_timer.presentation.configuration_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.cromulent.box_timer.domain.TimerSettings

@Composable
fun ConfigurationScreenRoot(
    viewModel: ConfigurationViewModel,
    modifier: Modifier = Modifier,
    navigateToTimerScreen: () -> Unit,
    navigateToSettings: () -> Unit,
) {

    val timerSettings = viewModel.timerSettings.collectAsState().value

    ConfigurationScreen(
        timerSettings,
        modifier,
        onStartWorkout = {
            viewModel.onAction(ConfigurationActions.SaveTimerSettings(it))
            navigateToTimerScreen()
        },
        navigateToSettings = navigateToSettings
    )

}
