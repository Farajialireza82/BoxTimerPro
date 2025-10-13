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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.back_ic
import boxtimerpro.composeapp.generated.resources.button_continue
import boxtimerpro.composeapp.generated.resources.button_pause
import boxtimerpro.composeapp.generated.resources.button_reset
import boxtimerpro.composeapp.generated.resources.button_resume
import boxtimerpro.composeapp.generated.resources.button_start
import boxtimerpro.composeapp.generated.resources.button_stop_exit
import boxtimerpro.composeapp.generated.resources.chip_round_number
import boxtimerpro.composeapp.generated.resources.chip_total_rounds
import boxtimerpro.composeapp.generated.resources.dialog_timer_running_message
import boxtimerpro.composeapp.generated.resources.dialog_timer_running_title
import boxtimerpro.composeapp.generated.resources.header_subtitle_ready_to_train
import boxtimerpro.composeapp.generated.resources.header_title_app
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.presentation.theme.SecondarySubtitleColor
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.theme.IceColorScheme
import com.cromulent.box_timer.presentation.timer_screen.components.Chip
import com.cromulent.box_timer.presentation.timer_screen.components.calculateOptimalTextSize
import com.cromulent.box_timer.presentation.timer_screen.components.RectangleButton
import com.cromulent.box_timer.presentation.timer_screen.components.TimerCircleIndicator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Exclamation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun TimerScreenRoot(
    viewModel: TimerViewModel,
    closeTimerScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    var showExitDialog by remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsStateWithLifecycle()


    BackHandler(true) {
        if (state.value.isTimerRunning || state.value.isPaused) {
            showExitDialog = true
        } else {
            closeTimerScreen()
        }
    }

    BoxWithConstraints {
        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            TimerScreenLandscape(
                state = state.value,
                onAction = viewModel::onAction,
                onBackButtonClicked = {
                    if (state.value.isTimerRunning || state.value.isPaused) {
                        showExitDialog = true
                    } else {
                        closeTimerScreen()
                    }
                },
                modifier = modifier
            )
        } else {
            TimerScreenPortrait(
                state = state.value,
                onAction = viewModel::onAction,
                onBackButtonClicked = {
                    if (state.value.isTimerRunning || state.value.isPaused) {
                        showExitDialog = true
                    } else {
                        closeTimerScreen()
                    }
                },
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

    }


}

@Composable
private fun TimerScreenLandscape(
    state: TimerState = TimerState(),
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Calculate shared text size for both chips
    val textMeasurer = rememberTextMeasurer()
    val roundText = "Round ${state.currentRound}"
    val ofText = "Of ${state.totalRounds}"
    
    // Calculate optimal text sizes for both texts
    val roundTextSize = calculateOptimalTextSize(
        textMeasurer = textMeasurer,
        text = roundText,
        maxWidth = 108, // 140dp - 32dp padding
        maxHeight = 44, // 68dp - 24dp padding
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W800,
            color = MaterialTheme.colorScheme.secondary
        )
    )
    
    val ofTextSize = calculateOptimalTextSize(
        textMeasurer = textMeasurer,
        text = ofText,
        maxWidth = 108,
        maxHeight = 44,
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W800,
            color = MaterialTheme.colorScheme.secondary
        )
    )
    
    // Use the smaller of the two sizes for both chips
    val sharedTextSize = minOf(roundTextSize.value, ofTextSize.value).sp

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
                        text = "Round ${state.currentRound}",
                        textSize = sharedTextSize
                    )

                    Chip(
                        modifier = Modifier
                            .width(140.dp)
                            .height(68.dp),
                        text = "Of ${state.totalRounds}",
                        textSize = sharedTextSize
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
                                blurRadius = if (state.isTimerRunning) 30f else 0f
                            ),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W900,
                            color = Color.White,
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
                            fontWeight = FontWeight.W600,
                            letterSpacing = 6.sp,
                            textAlign = TextAlign.Center,
                            color = if (state.isTimerRunning) MaterialTheme.colorScheme.secondary else Color.LightGray
                        ),
                        text = state.timerMessage.uppercase(), // Assuming timerMessage is dynamic and doesn't need external string res
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
                    text = if (state.isTimerRunning) stringResource(Res.string.button_pause) else stringResource(
                        Res.string.button_start
                    ),
                )

                Spacer(Modifier.size(18.dp))


                RectangleButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp),
                    isActive = false,
                    text = stringResource(Res.string.button_reset),
                    onButtonClicked = {
                        onAction(TimerActions.ResetTimer)
                    }
                )

            }

        }


    }

}

@Preview(widthDp = 350, heightDp = 720)
@Composable
fun TimerScreenPortrait(
    state: TimerState = TimerState(),
    onAction: (TimerActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    // Calculate shared text size for both chips
    val textMeasurer = rememberTextMeasurer()
    val roundText = "Round ${state.currentRound}"
    val ofText = "Of ${state.totalRounds}"
    
    // Calculate optimal text sizes for both texts
    val roundTextSize = calculateOptimalTextSize(
        textMeasurer = textMeasurer,
        text = roundText,
        maxWidth = 200, // Approximate chip width minus padding
        maxHeight = 50, // Approximate chip height minus padding
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W800,
            color = MaterialTheme.colorScheme.secondary
        )
    )
    
    val ofTextSize = calculateOptimalTextSize(
        textMeasurer = textMeasurer,
        text = ofText,
        maxWidth = 200,
        maxHeight = 50,
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W800,
            color = MaterialTheme.colorScheme.secondary
        )
    )
    
    // Use the smaller of the two sizes for both chips
    val sharedTextSize = minOf(roundTextSize.value, ofTextSize.value).sp

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
                    text = "Round ${state.currentRound}",
                    textSize = sharedTextSize
                )

                Chip(
                    modifier = Modifier
                        .aspectRatio(1.9f)
                        .padding(horizontal = 18.dp, vertical = 6.dp)
                        .weight(1f),
                    text = "Of ${state.totalRounds}",
                    textSize = sharedTextSize
                )

            }

            TimerCircleIndicator(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(2f),
                remainingTime = state.remainingTime,
                progress = state.progress,
                isRunning = state.isTimerRunning,
                message = state.timerMessage, // Assuming timerMessage is dynamic and doesn't need external string res
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


                RectangleButton(
                    modifier = Modifier
                        .weight(if (state.isTimerRunning) 4f else 3f),
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
                    text = when {
                        state.isTimerRunning -> stringResource(Res.string.button_pause)
                        state.isPaused -> stringResource(Res.string.button_resume)
                        else -> stringResource(Res.string.button_start)
                    },
                )

                Spacer(Modifier.size(18.dp))


                RectangleButton(
                    modifier = Modifier
                        .weight(2f),
                    isActive = false,
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
fun ExitTimerDialog(
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