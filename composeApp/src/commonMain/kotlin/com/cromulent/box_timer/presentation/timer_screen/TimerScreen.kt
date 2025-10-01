package com.cromulent.box_timer.presentation.timer_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.FruitOrange
import com.cromulent.box_timer.core.theme.GoldenSun
import com.cromulent.box_timer.core.theme.SubtitleColor
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.timer_screen.components.Chip
import com.cromulent.box_timer.presentation.timer_screen.components.CircleButton
import com.cromulent.box_timer.presentation.timer_screen.components.RectangleButton
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
    state: TimerState = TimerState(),
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
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Chip(
                        modifier = Modifier
                            .width(150.dp)
                            .height(78.dp),
                        fontSize = 24.sp,
                        text = "Round ${state.currentRound}"
                    )

                    Chip(
                        modifier = Modifier
                            .width(150.dp)
                            .height(78.dp),
                        fontSize = 24.sp,
                        text = "of ${state.totalRounds}"
                    )

                }

                TimerCircleIndicator(
                    currentTimeMillis = state.currentTime,
                    totalTimeMillis = state.roundDuration,
                    isRunning = state.isTimerRunning,
                    message = state.timerMessage,
                )

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                    RectangleButton(
                        isActive = state.isTimerRunning,
                        onButtonClicked = {
                            if (state.isTimerRunning) {
                                onAction(TimerActions.PauseTimer)
                            } else {
                                onAction(TimerActions.StartTimer)
                            }
                        },
                        unactiveColor = FruitOrange,
                        activeColor = CoralHaze,
                        text = if (state.isTimerRunning) "Stop" else "Start",
                    )

                    Spacer(Modifier.size(18.dp))


                    RectangleButton(
                        modifier = Modifier
                            .height(58.dp),
                        isActive = false,
                        text = "Reset",
                        onButtonClicked = {
                            onAction(TimerActions.ResetTimer)
                        }
                    )

                }


            }
        }
    }
}

