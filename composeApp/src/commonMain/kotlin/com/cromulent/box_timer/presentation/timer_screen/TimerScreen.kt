package com.cromulent.box_timer.presentation.timer_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.back_ic
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.presentation.theme.SecondarySubtitleColor
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.timer_screen.components.Chip
import com.cromulent.box_timer.presentation.timer_screen.components.RectangleButton
import com.cromulent.box_timer.presentation.timer_screen.components.TimerCircleIndicator
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerScreenRoot(
    viewModel: TimerViewModel,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    BoxWithConstraints {
        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            TimerScreenLandscape(
                state = state.value,
                onAction = viewModel::onAction,
                onBackButtonClicked = onBackButtonClicked,
                modifier = modifier
            )
        } else {
            TimerScreenPortrait(
                state = state.value,
                onAction = viewModel::onAction,
                onBackButtonClicked = onBackButtonClicked,
                modifier = modifier
            )
        }
    }


}
@Composable
private fun TimerScreenLandscape(
    state: TimerState = TimerState(),
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    BoxTimerProTheme {

        Box(
            modifier = modifier
                .background(Color.Transparent)
                .statusBarsPadding()
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            IconButton(
                onClick = onBackButtonClicked,
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    painter = painterResource(Res.drawable.back_ic),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement
                            .spacedBy(
                                space = 24.dp,
                                alignment = Alignment.CenterHorizontally
                            )
                    ) {

                        Chip(
                            modifier = Modifier
                                .width(140.dp)
                                .height(68.dp),
                            fontSize = 20.sp,
                            text = "Round ${state.currentRound}"
                        )

                        Chip(
                            modifier = Modifier
                                .width(140.dp)
                                .height(68.dp),
                            fontSize = 20.sp,
                            text = "of ${state.totalRounds}"
                        )

                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = formatTime(state.roundDuration - state.currentTime),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.secondary,
                                offset = Offset(0f, 4f),
                                blurRadius = if(state.isTimerRunning) 30f else 0f
                            ),
                        ),
                        fontSize = 150.sp,
                        fontWeight = FontWeight.W900,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .animateContentSize(),
                        text = state.timerMessage.uppercase(),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.W600,
                        letterSpacing = 6.sp,
                        textAlign = TextAlign.Center,
                        color = if (state.isTimerRunning) MaterialTheme.colorScheme.secondary else Color.LightGray
                    )

                }

                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 16.dp),
                    color = SecondarySubtitleColor,
                    thickness = 2.dp
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .animateContentSize()
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                ) {

                    RectangleButton(
                        modifier = Modifier
                            .weight(if (state.isTimerRunning) 2f else 1f),
                        isActive = state.isTimerRunning,
                        onButtonClicked = {
                            if (state.isTimerRunning) {
                                onAction(TimerActions.PauseTimer)
                            } else {
                                onAction(TimerActions.StartTimer)
                            }
                        },
                        unactiveColor = MaterialTheme.colorScheme.tertiary,
                        activeColor = MaterialTheme.colorScheme.secondary,
                        text = if (state.isTimerRunning) "Pause" else if (state.currentTime != 0L) "Resume" else "Start",
                    )

                    Spacer(Modifier.size(18.dp))


                    RectangleButton(
                        modifier = Modifier
                            .weight(1f)
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

@Preview
@Composable
private fun TimerScreenPortrait(
    state: TimerState = TimerState(),
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {


    MaterialTheme {

        Scaffold(
            modifier = Modifier,
            containerColor = Color.Transparent,
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
                    .padding(it)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp)
                        .weight(0.3f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Chip(
                        modifier = Modifier
                            .aspectRatio(1.9f)
                            .padding(horizontal = 18.dp, vertical = 6.dp)
                            .weight(1f),
                        fontSize = 24.sp,
                        text = "Round ${state.currentRound}"
                    )

                    Chip(
                        modifier = Modifier
                            .aspectRatio(1.9f)
                            .padding(horizontal = 18.dp, vertical = 6.dp)
                            .weight(1f),
                        fontSize = 24.sp,
                        text = "of ${state.totalRounds}"
                    )

                }

                TimerCircleIndicator(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .weight(2f)
                    ,
                    currentTimeMillis = state.currentTime,
                    totalTimeMillis = state.roundDuration,
                    isRunning = state.isTimerRunning,
                    message = state.timerMessage,
                )

                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .weight(1f)
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
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
                        unactiveColor = MaterialTheme.colorScheme.tertiary,
                        activeColor = MaterialTheme.colorScheme.secondary,
                        text = if (state.isTimerRunning) "Pause" else if (state.currentTime != 0L) "Resume" else "Start",
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

