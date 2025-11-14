package com.cromulent.box_timer.presentation.timer_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.back_ic
import boxtimerpro.composeapp.generated.resources.button_continue
import boxtimerpro.composeapp.generated.resources.button_got_it
import boxtimerpro.composeapp.generated.resources.button_pause
import boxtimerpro.composeapp.generated.resources.button_reset
import boxtimerpro.composeapp.generated.resources.button_resume
import boxtimerpro.composeapp.generated.resources.button_start
import boxtimerpro.composeapp.generated.resources.button_stop_exit
import boxtimerpro.composeapp.generated.resources.chip_round_number
import boxtimerpro.composeapp.generated.resources.chip_total_rounds
import boxtimerpro.composeapp.generated.resources.countdown_get_ready
import boxtimerpro.composeapp.generated.resources.dialog_timer_running_message
import boxtimerpro.composeapp.generated.resources.dialog_timer_running_title
import boxtimerpro.composeapp.generated.resources.dialog_workout_complete_message
import boxtimerpro.composeapp.generated.resources.dialog_workout_complete_title
import boxtimerpro.composeapp.generated.resources.header_subtitle_ready_to_train
import boxtimerpro.composeapp.generated.resources.header_title_app
import boxtimerpro.composeapp.generated.resources.skip_next_24px
import boxtimerpro.composeapp.generated.resources.title_counting_down
import boxtimerpro.composeapp.generated.resources.title_fight
import boxtimerpro.composeapp.generated.resources.title_paused
import boxtimerpro.composeapp.generated.resources.title_ready
import boxtimerpro.composeapp.generated.resources.title_rest
import boxtimerpro.composeapp.generated.resources.title_workout_complete
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.theme.BoxTimerProThemePrv
import com.cromulent.box_timer.presentation.theme.SecondarySubtitleColor
import com.cromulent.box_timer.presentation.theme.colorSchemes
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.CountDown
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.Ready
import com.cromulent.box_timer.presentation.timer_screen.components.Chip
import com.cromulent.box_timer.presentation.timer_screen.components.RectangleButton
import com.cromulent.box_timer.presentation.timer_screen.components.TimerCircleIndicator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bong
import compose.icons.fontawesomeicons.solid.CheckCircle
import compose.icons.fontawesomeicons.solid.Exclamation
import compose.icons.fontawesomeicons.solid.Helicopter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
private fun getStringResource(key: String): String {
    return when (key) {
        "title_ready" -> stringResource(Res.string.title_ready)
        "title_fight" -> stringResource(Res.string.title_fight)
        "title_paused" -> stringResource(Res.string.title_paused)
        "title_rest" -> stringResource(Res.string.title_rest)
        "title_counting_down" -> stringResource(Res.string.title_counting_down)
        "title_workout_complete" -> stringResource(Res.string.title_workout_complete)
        else -> key
    }
}

@Composable
private fun translateCountdownText(countdownText: String): String {
    return if (countdownText.startsWith("Get Ready: ")) {
        val number = countdownText.substringAfter("Get Ready: ").toIntOrNull() ?: 0
        stringResource(Res.string.countdown_get_ready) + number
    } else {
        countdownText
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimerScreenRoot(
    viewModel: TimerViewModel,
    state: TimerState,
    closeTimerScreen: () -> Unit,
    modifier: Modifier
) {

    var showExitDialog by remember { mutableStateOf(false) }
    BackHandler(true) {
        if (state.timerStatus != Ready) {
            showExitDialog = true
        } else {
            closeTimerScreen()
        }
    }

    val onBackButtonClicked = {
        if (state.timerStatus != Ready) showExitDialog = true
        else closeTimerScreen()
    }

    BoxWithConstraints {
        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            TimerScreenLandscape(
                state = state,
                onAction = viewModel::onAction,
                onBackButtonClicked = onBackButtonClicked,
                modifier = modifier
            )
        } else {
            TimerScreenPortrait(
                state = state,
                onAction = viewModel::onAction,
                onBackButtonClicked = onBackButtonClicked,
                modifier = modifier
            )
        }

        if (showExitDialog) {
            ExitTimerDialog(
                onDismiss = {
                    showExitDialog = false
                },
                onConfirmExit = {
                    showExitDialog = false
                    closeTimerScreen()
                }
            )
        }

        if (state.timerStatus == TimerStatus.Completed) {
            WorkoutCompleteDialog(
                onComplete = {
                    viewModel.onAction(TimerActions.CompleteWorkout)
                }
            )
        }

    }


}


@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun TimerScreenLandscape(
    state: TimerState = TimerState(),
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    var chipTextSize by remember { mutableStateOf(16.sp) }

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
                        .weight(1.5f)
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
                        paddingValues = PaddingValues(
                            vertical = 4.dp,
                            horizontal = 8.dp
                        ),
                        text = stringResource(Res.string.chip_round_number) + state.currentRound,
                        onSizeChanged = {
                            chipTextSize = it
                        }
                    )

                    Chip(
                        modifier = Modifier
                            .width(140.dp)
                            .height(68.dp),
                        paddingValues = PaddingValues(
                            vertical = 4.dp,
                            horizontal = 8.dp
                        ),
                        text = stringResource(Res.string.chip_total_rounds) + state.totalRounds,
                        textSize = chipTextSize
                    )

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(3f),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = formatTime(state.remainingTime),
                        autoSize = TextAutoSize.StepBased(maxFontSize = 400.sp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.secondary,
                                offset = Offset(0f, 4f),
                                blurRadius = if (state.isInActiveState()) 30f else 0f
                            ),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W900,
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )
                }

                Box(
                    Modifier
                        .weight(1.3f)
                        .animateContentSize()
                ) {
                    BasicText(
                        modifier = Modifier
                            .animateContentSize()
                            .align(Alignment.Center),
                        autoSize = TextAutoSize.StepBased(minFontSize = 48.sp),
                        style = TextStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                            fontWeight = FontWeight.W600,
                            letterSpacing = 6.sp,
                            textAlign = TextAlign.Center,
                            color = if (state.isInActiveState()) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.7f
                            )
                        ),
                        text = if (state.timerStatus == CountDown) translateCountdownText(state.countDownText) else getStringResource(
                            state.timerStatus.messageKey
                        ).uppercase(),
                    )
                }

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

                Row(
                    modifier = Modifier
                        .weight(if (state.isInActiveState()) 2f else 1f)
                ) {

                    RectangleButton(
                        modifier = Modifier
                            .weight(3f),
                        isActive = state.isInActiveState(),
                        onButtonClicked = {
                            if (state.timerStatus == CountDown) return@RectangleButton
                            if (state.isInActiveState()) {
                                onAction(TimerActions.PauseTimer)
                            } else {
                                onAction(TimerActions.StartTimer)
                            }
                        },
                        unactiveColor = MaterialTheme.colorScheme.tertiary,
                        activeColor = MaterialTheme.colorScheme.secondary,
                        activeTextColor = MaterialTheme.colorScheme.onSecondary,
                        unactiveTextColor = MaterialTheme.colorScheme.onTertiary,
                        text = when {
                            state.timerStatus == CountDown -> stringResource(Res.string.title_counting_down)
                            state.isInActiveState() -> stringResource(Res.string.button_pause)
                            state.timerStatus == TimerStatus.Paused -> stringResource(Res.string.button_resume)
                            else -> stringResource(Res.string.button_start)
                        },
                    )

                    Spacer(Modifier.size(4.dp))



                    RectangleButton(
                        modifier = Modifier
                            .weight(1f),
                        isActive = state.isInActiveState(),
                        onButtonClicked = {
//                                onAction(TimerActions.SkipTimer)
                        },
                        unactiveColor = MaterialTheme.colorScheme.tertiary,
                        activeColor = MaterialTheme.colorScheme.onError,
                        activeTextColor = MaterialTheme.colorScheme.onSecondary,
                        unactiveTextColor = MaterialTheme.colorScheme.onTertiary,
                        text = "Skip",
                    )


                }

                Spacer(Modifier.size(18.dp))


                RectangleButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp),
                    isActive = false,
                    unactiveTextColor = Color.White,
                    text = stringResource(Res.string.button_reset),
                    onButtonClicked = {
                        onAction(TimerActions.ResetTimer)
                    }
                )

            }

        }


    }

}

@Composable
private fun TimerScreenPortrait(
    state: TimerState = TimerState(),
    onAction: (TimerActions) -> Unit = {},
    onBackButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    var chipTextSize by remember { mutableStateOf(16.sp) }

    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        topBar = {
            Header(
                modifier = Modifier
                    .statusBarsPadding(),
                title = stringResource(Res.string.header_title_app),
                subtitle = stringResource(Res.string.header_subtitle_ready_to_train),
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
                    text = stringResource(Res.string.chip_round_number) + state.currentRound,
                    onSizeChanged = {
                        chipTextSize = it
                    }
                )

                Chip(
                    modifier = Modifier
                        .aspectRatio(1.9f)
                        .padding(horizontal = 18.dp, vertical = 6.dp)
                        .weight(1f),
                    text = stringResource(Res.string.chip_total_rounds) + state.totalRounds,
                    textSize = chipTextSize
                )

            }

            TimerCircleIndicator(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(2f),
                remainingTime = state.remainingTime,
                progress = state.progress,
                isRunning = state.isInActiveState(),
                message = if (state.timerStatus == CountDown) translateCountdownText(state.countDownText) else getStringResource(
                    state.timerStatus.messageKey
                )
            )

            Column(
                modifier = Modifier
                    .padding(bottom = 12.dp, top = 6.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Row(
                    modifier = Modifier
                        .weight(if (state.isInActiveState()) 3f else 2f)
                        .animateContentSize()
                ) {


                    //Start/Pause button
                    RectangleButton(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxHeight(),
                        isActive = state.isInActiveState(),
                        onButtonClicked = {
                            if (state.timerStatus == CountDown) return@RectangleButton
                            if (state.isInActiveState()) {
                                onAction(TimerActions.PauseTimer)
                            } else {
                                onAction(TimerActions.StartTimer)
                            }
                        },
                        unactiveColor = MaterialTheme.colorScheme.tertiary,
                        activeColor = MaterialTheme.colorScheme.secondary,
                        activeTextColor = MaterialTheme.colorScheme.onSecondary,
                        unactiveTextColor = MaterialTheme.colorScheme.onTertiary,
                        text = when {
                            state.timerStatus == CountDown -> stringResource(Res.string.title_counting_down)
                            state.isInActiveState() -> stringResource(Res.string.button_pause)
                            state.timerStatus == TimerStatus.Paused -> stringResource(Res.string.button_resume)
                            else -> stringResource(Res.string.button_start)
                        },
                    )

                    if (state.timerStatus != Ready && state.timerStatus != CountDown) {

                        Spacer(Modifier.size(8.dp))

                        RectangleButton(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxHeight(),
                            isActive = state.isInActiveState(),
                            onButtonClicked = {
                                onAction(TimerActions.SkipTimer)
                            },
                            unactiveColor = Color(0xFFf59f0a),
                            activeColor = Color(0xFFf59f0a),
                            activeTextColor = MaterialTheme.colorScheme.onSecondary,
                            unactiveTextColor = MaterialTheme.colorScheme.onSecondary,
                            icon = painterResource(Res.drawable.skip_next_24px),
                        )
                    }


                }

                Spacer(Modifier.size(18.dp))


                RectangleButton(
                    modifier = Modifier
                        .weight(2f),
                    isActive = false,
                    unactiveTextColor = White,
                    text = stringResource(Res.string.button_reset),
                    onButtonClicked = {
                        onAction(TimerActions.ResetTimer)
                    }
                )
            }
        }


    }
}

@Composable
private fun ExitTimerDialog(
    onDismiss: () -> Unit,
    onConfirmExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Warning Icon
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Exclamation,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(Res.string.dialog_timer_running_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(Res.string.dialog_timer_running_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.button_continue),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = onConfirmExit,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.button_stop_exit),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkoutCompleteDialog(
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = { /* Prevent dismissing by clicking outside */ },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Success Icon
                Icon(
                    imageVector = FontAwesomeIcons.Solid.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(Res.string.dialog_workout_complete_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(Res.string.dialog_workout_complete_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onComplete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.button_got_it),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {

    BoxTimerProThemePrv(
        colorScheme = colorSchemes[0].lightColorScheme
    ) {

        TimerScreenPortrait()
    }

}