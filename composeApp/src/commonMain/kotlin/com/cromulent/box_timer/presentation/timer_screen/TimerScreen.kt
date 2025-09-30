package com.cromulent.box_timer.presentation.timer_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.timer_screen.components.Chip
import com.cromulent.box_timer.presentation.timer_screen.components.CircleButton
import com.cromulent.box_timer.presentation.timer_screen.components.TimerCircleIndicator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.FileAudio
import compose.icons.fontawesomeicons.solid.Pause
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Stop
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerScreenRoot(
    viewModel: TimerViewModel,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    TimerScreen(
        state = state.value,
        onAction = viewModel::onAction,
        onBackButtonClicked = onBackButtonClicked,
        modifier = modifier
    )


}

@Composable
@Preview
private fun TimerScreen(
    state: TimerState,
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {


    MaterialTheme {

        Scaffold(
            modifier = Modifier,
            topBar = {
                Header(
                    modifier = Modifier
                        .statusBarsPadding(),
                    title = "BoxTimer Pro",
                    subtitle = "Ready to train",
                    hasBackButton = true,
                    onBackButtonClicked = onBackButtonClicked
                )

            }
        ) {

            Column(
                modifier = modifier
                    .background(backgroundGradientBrush)
                    .padding(it)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Chip("Round ${state.currentRound}")
                    Chip("of ${state.totalRounds}")

                }

                Spacer(
                    Modifier.size(24.dp)
                )

                TimerCircleIndicator(
                    currentTimeMillis = state.currentTime,
                    totalTimeMillis = state.roundDuration,
                    isRunning = state.isTimerRunning,
                    message = state.timerMessage,
                )

                Spacer(
                    Modifier.size(24.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CircleButton(
                        isActive = state.isTimerRunning,
                        onButtonClicked = {
                            if (state.isTimerRunning) {
                                onAction(TimerActions.PauseTimer)
                            } else {
                                onAction(TimerActions.StartTimer)
                            }
                        },
                        icon = if (state.isTimerRunning) FontAwesomeIcons.Solid.Pause else FontAwesomeIcons.Solid.Play
                    )


                    CircleButton(
                        isActive = false,
                        onButtonClicked = {
                            onAction(TimerActions.ResetTimer)
                        },
                        icon = FontAwesomeIcons.Solid.Stop
                    )

                }


            }
        }
    }
}

