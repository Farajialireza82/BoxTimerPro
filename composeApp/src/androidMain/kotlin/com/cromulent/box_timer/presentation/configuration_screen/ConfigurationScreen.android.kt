package com.cromulent.box_timer.presentation.configuration_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.cromulent.box_timer.core.util.rememberNotificationPermissionState
import com.cromulent.box_timer.domain.TimerSettings

@Composable
fun ConfigurationScreenRoot(
    viewModel: ConfigurationViewModel,
    modifier: Modifier = Modifier,
    navigateToTimerScreen: () -> Unit,
    navigateToSettings: () -> Unit,
) {

    val timerSettings = viewModel.timerSettings.collectAsState().value
    val isBatteryOptimizationEnabled by viewModel.isBatteryOptimizationEnabled.collectAsState()
    val notificationPermissionState = rememberNotificationPermissionState { isGranted ->
        // Permission result handled - user can proceed to timer screen regardless
        navigateToTimerScreen()
    }

    ConfigurationScreen(
        timerSettings = timerSettings,
        shouldShowBatteryDialog = isBatteryOptimizationEnabled,
        modifier = modifier,
        onStartWorkout = { settings ->
            viewModel.onAction(ConfigurationActions.SaveTimerSettings(settings))
            // Request notification permission before navigating
            if (!notificationPermissionState.hasPermission && !notificationPermissionState.permissionRequested) {
                notificationPermissionState.requestPermission()
            } else {
                navigateToTimerScreen()
            }
        },
        dismissBatteryOptimizationDialog = {
            viewModel.onAction(ConfigurationActions.DismissBatteryOptimizationDialog)
        },
        openOptimizationSettings = {
            viewModel.onAction(ConfigurationActions.OpenOptimizationSettings)
        },
        navigateToSettings = navigateToSettings
    )

}
