package com.cromulent.box_timer.presentation.timer_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist
import com.cromulent.box_timer.core.theme.SubtitleColor
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.core.theme.titleGradientBrush
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.timer_screen.components.Chip
import com.cromulent.box_timer.presentation.timer_screen.components.CircleButton
import com.cromulent.box_timer.presentation.timer_screen.components.TimerCircleIndicator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.StopCircle
import compose.icons.fontawesomeicons.solid.Pause
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Stop
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerScreenRoot(
    viewmodel: TimerViewmodel,
    modifier: Modifier = Modifier
) {

    val state = viewmodel.state.collectAsStateWithLifecycle()

    TimerScreen(
        state = state.value,
        onAction = viewmodel::onAction,
        modifier = modifier
    )


}

@Composable
@Preview
private fun TimerScreen(
    state: TimerState,
    onAction: (TimerActions) -> Unit,
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
                    subtitle = "Ready to train"
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
                    currentTimeMillis = state.currentTimeMillis,
                    totalTimeMillis = state.totalTimeMillis,
                    isRunning = state.isTimerRunning
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

