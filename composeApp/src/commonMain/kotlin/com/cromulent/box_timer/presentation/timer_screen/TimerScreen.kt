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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.back_ic
import boxtimerpro.composeapp.generated.resources.button_pause
import boxtimerpro.composeapp.generated.resources.button_reset
import boxtimerpro.composeapp.generated.resources.button_resume
import boxtimerpro.composeapp.generated.resources.button_start
import boxtimerpro.composeapp.generated.resources.header_subtitle_ready_to_train
import boxtimerpro.composeapp.generated.resources.header_title_app
import boxtimerpro.composeapp.generated.resources.ic_flag
import boxtimerpro.composeapp.generated.resources.skip_next_24px
import boxtimerpro.composeapp.generated.resources.title_counting_down
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.core.util.getStringResource
import com.cromulent.box_timer.core.util.translateCountdownText
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.theme.BoxTimerProThemePrv
import com.cromulent.box_timer.presentation.theme.colorSchemes
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.CountDown
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.Ready
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.Running
import com.cromulent.box_timer.presentation.timer_screen.components.RectangleButton
import com.cromulent.box_timer.presentation.timer_screen.components.RoundsRow
import com.cromulent.box_timer.presentation.timer_screen.components.TimerCircleIndicator
import com.cromulent.box_timer.presentation.timer_screen.components.dialogs.ExitTimerDialog
import com.cromulent.box_timer.presentation.timer_screen.components.dialogs.LapList
import com.cromulent.box_timer.presentation.timer_screen.components.dialogs.WorkoutCompleteDialog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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


@Composable
private fun TimerScreenLandscape(
    state: TimerState = TimerState(timerStatus = Running),
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .background(Transparent)
            .statusBarsPadding()
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
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

                RoundsRow(
                    modifier = Modifier
                        .weight(if(state.laps.isNotEmpty()) 1.8f else 1.3f)
                        .padding(12.dp),
                    state = state
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(3.5f),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = formatTime(state.remainingTime),
                        autoSize = TextAutoSize.StepBased(maxFontSize = 400.sp),
                        style = TextStyle(
                            shadow = Shadow(
                                color = if(state.timerStatus == Running) MaterialTheme.colorScheme.secondary else Transparent,
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
                        .weight(0.9f)
                        .animateContentSize()
                ) {
                    BasicText(
                        modifier = Modifier
                            .animateContentSize()
                            .align(Alignment.Center),
                        autoSize = TextAutoSize.StepBased(minFontSize = 24.sp, stepSize = 12.sp),
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

                if (state.laps.isNotEmpty()) {

                    LapList(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(2f)
                            .padding(8.dp),
                        laps = state.laps,
                        roundDuration = state.roundDuration,
                        deleteLap = { onAction(TimerActions.DeleteLap(it)) }
                    )
                }

            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 16.dp),
                color = Color(0xFF353130),
                thickness = 2.dp
            )

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .animateContentSize()
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                ) {

                    RectangleButton(
                        modifier = Modifier
                            .weight(if (state.isInActiveState()) 2f else 1f),
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

                    Spacer(Modifier.size(8.dp))



                    RectangleButton(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        isActive = false,
                        unactiveTextColor = White,
                        text = stringResource(Res.string.button_reset),
                        onButtonClicked = {
                            onAction(TimerActions.ResetTimer)
                        }
                    )

                    Spacer(Modifier.size(8.dp))

                    if (state.timerStatus == Running || (state.timerStatus != Ready && state.timerStatus != CountDown)) {

                        Row(
                            modifier = Modifier
                                .weight(1f)
                        ) {

                            if (state.timerStatus == Running) {
                                //Lap Button
                                RectangleButton(
                                    modifier = Modifier
                                        .weight(2f),
                                    isActive = false,
                                    onButtonClicked = {
                                        onAction(TimerActions.LapTimer)
                                    },
                                    unactiveColor = MaterialTheme.colorScheme.secondary,
                                    activeColor = MaterialTheme.colorScheme.secondary,
                                    unactiveTextColor = MaterialTheme.colorScheme.onSecondary,
                                    icon = painterResource(Res.drawable.ic_flag),
                                )
                            }

                            if (state.timerStatus != Ready && state.timerStatus != CountDown) {

                                Spacer(Modifier.size(4.dp))

                                RectangleButton(
                                    modifier = Modifier
                                        .weight(2f),
                                    isActive = false,
                                    onButtonClicked = {
                                        onAction(TimerActions.SkipTimer)
                                    },
                                    unactiveColor = MaterialTheme.colorScheme.secondary,
                                    activeColor = MaterialTheme.colorScheme.secondary,
                                    activeTextColor = MaterialTheme.colorScheme.onSecondary,
                                    unactiveTextColor = MaterialTheme.colorScheme.onSecondary,
                                    icon = painterResource(Res.drawable.skip_next_24px),
                                )

                            }
                        }
                    }

                }
            }
        }


    }


}

@Composable
private fun TimerScreenPortrait(
    state: TimerState = TimerState(
        timerStatus = Running
    ),
    onAction: (TimerActions) -> Unit = {},
    onBackButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {

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

            RoundsRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.33f),
                paddingValues = PaddingValues(if(state.laps.isNotEmpty()) 10.dp else 16.dp),
                state = state)

            Spacer(Modifier.size(12.dp))

            TimerCircleIndicator(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(2f),
                remainingTime = state.remainingTime,
                progress = state.progress,
                timerStatus = state.timerStatus,
                message = if (state.timerStatus == CountDown) translateCountdownText(state.countDownText) else getStringResource(
                    state.timerStatus.messageKey
                )
            )

            if (state.laps.isNotEmpty()) {
                LapList(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f),
                    laps = state.laps,
                    roundDuration = state.roundDuration,
                    deleteLap = {
                        lap -> onAction(TimerActions.DeleteLap(lap))
                    }
                )

            }

            Spacer(Modifier.size(8.dp))


            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
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

                        if (state.timerStatus == Running) {

                            Spacer(Modifier.size(8.dp))

                            RectangleButton(
                                modifier = Modifier
                                    .weight(3f)
                                    .fillMaxHeight(),
                                isActive = state.isInActiveState(),
                                onButtonClicked = {
                                    onAction(TimerActions.LapTimer)
                                },
                                unactiveColor = MaterialTheme.colorScheme.tertiary,
                                activeColor = MaterialTheme.colorScheme.secondary,
                                activeTextColor = MaterialTheme.colorScheme.onSecondary,
                                unactiveTextColor = MaterialTheme.colorScheme.onSecondary,
                                icon = painterResource(Res.drawable.ic_flag),
                            )
                        }


                    }

                    Spacer(Modifier.size(8.dp))

                    Row(
                        modifier = Modifier
                            .weight(2f)
                    ) {

                        RectangleButton(
                            modifier = Modifier
                                .weight(5f),
                            isActive = false,
                            unactiveTextColor = White,
                            text = stringResource(Res.string.button_reset),
                            onButtonClicked = {
                                onAction(TimerActions.ResetTimer)
                            }
                        )

                        if (state.timerStatus != Ready && state.timerStatus != CountDown) {


                            Spacer(Modifier.size(8.dp))

                            RectangleButton(
                                modifier = Modifier
                                    .weight(3f),
                                isActive = false,
                                unactiveTextColor = White,
                                icon = painterResource(Res.drawable.skip_next_24px),
                                onButtonClicked = {
                                    onAction(TimerActions.SkipTimer)
                                }
                            )
                        }
                    }
                }
            }
        }


    }
}

// Portrait Previews - Light Mode


@Preview
@Composable
private fun PrevScheme0Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[0].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme1Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[1].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme2Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[2].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme3Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[3].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme4Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[4].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme5Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[5].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme6Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[6].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme7Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[7].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme8Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[8].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme9Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[9].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme10Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[10].lightColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme11Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[11].lightColorScheme) {
        TimerScreenPortrait()
    }
}

// Portrait Previews - Dark Mode
@Preview
@Composable
private fun PrevScheme0Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[0].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme1Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[1].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme2Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[2].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme3Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[3].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme4Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[4].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme5Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[5].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme6Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[6].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme7Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[7].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme8Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[8].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme9Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[9].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme10Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[10].darkColorScheme) {
        TimerScreenPortrait()
    }
}

@Preview
@Composable
private fun PrevScheme11Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[11].darkColorScheme) {
        TimerScreenPortrait()
    }
}

// Landscape Previews - Light Mode
@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme0Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[0].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme1Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[1].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme2Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[2].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme3Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[3].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme4Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[4].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme5Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[5].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme6Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[6].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme7Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[7].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme8Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[8].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme9Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[9].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme10Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[10].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme11Light() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[11].lightColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

// Landscape Previews - Dark Mode
@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme0Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[0].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme1Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[1].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme2Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[2].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme3Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[3].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme4Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[4].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme5Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[5].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme6Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[6].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme7Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[7].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme8Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[8].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme9Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[9].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 600, heightDp = 280)
@Composable
private fun LandscapeScheme10Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[10].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}

@Preview(widthDp = 800, heightDp = 340)
@Composable
private fun LandscapeScheme11Dark() {
    BoxTimerProThemePrv(colorScheme = colorSchemes[11].darkColorScheme) {
        TimerScreenLandscape(onAction = {}, onBackButtonClicked = {})
    }
}