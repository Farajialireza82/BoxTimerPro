package com.cromulent.box_timer.presentation.configuration_screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onVisibilityChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.settings_ic
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.core.util.toWorkoutMode
import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.configuration_screen.components.ModeCard
import com.cromulent.box_timer.presentation.configuration_screen.components.RoundNumberPicker
import com.cromulent.box_timer.presentation.configuration_screen.components.TimerSetter
import com.cromulent.box_timer.presentation.configuration_screen.components.WorkoutModeGrid
import com.cromulent.box_timer.presentation.configuration_screen.util.WorkoutMode
import com.cromulent.box_timer.presentation.theme.IceColorScheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Play
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ConfigurationScreenRoot(
    viewModel: ConfigurationViewModel,
    modifier: Modifier = Modifier,
    navigateToTimerScreen: () -> Unit,
    navigateToSettings: () -> Unit,
) {

    val timerSettings by viewModel.timerSettings.collectAsState()

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

@Composable
private fun ConfigurationScreen(
    timerSettings: TimerSettings = TimerSettings(
        roundDuration = 1000L,
        restDuration = 2L,
        totalRounds = 2
    ),
    modifier: Modifier = Modifier,
    onStartWorkout: (TimerSettings) -> Unit,
    navigateToSettings: () -> Unit,
) {

    var selectedMode by rememberSaveable { mutableStateOf(timerSettings.toWorkoutMode()) }
    var roundDuration by rememberSaveable { mutableLongStateOf(timerSettings.roundDuration) }
    var restDuration by rememberSaveable { mutableLongStateOf(timerSettings.restDuration) }
    var totalRounds by rememberSaveable { mutableIntStateOf(timerSettings.totalRounds) }

    val scrollState = rememberScrollState()

    var lastScrollValue by remember { mutableStateOf(0) }
    var isFabExpanded by remember { mutableStateOf(true) }

    var isFabVisible by remember { mutableStateOf(true) }

    LaunchedEffect(scrollState.value) {
        val scrollDiff = scrollState.value - lastScrollValue
        if (scrollState.value == 0) isFabExpanded = true
        else if (scrollDiff > 10) isFabExpanded = false
        else if (scrollDiff < -10) isFabExpanded = true
        lastScrollValue = scrollState.value
    }

    Scaffold(
        modifier = Modifier,
        containerColor = Transparent,
        floatingActionButton = {


            AnimatedVisibility(isFabVisible) {

                ExtendedFloatingActionButton(
                    onClick = {
                        onStartWorkout(
                            TimerSettings(roundDuration, restDuration, totalRounds)
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {

                    AnimatedVisibility(isFabExpanded) {
                        Row {
                            Text("Start Workout")
                            Spacer(Modifier.size(8.dp))
                        }
                    }

                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = FontAwesomeIcons.Solid.Play,
                        contentDescription = null
                    )
                }
            }
        }
    ) {

        Column(
            modifier = modifier
                .background(Transparent)
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Header(
                modifier = Modifier.padding(horizontal = 8.dp),
                title = "BoxTimer Pro",
                subtitle = "Configure Your Workout",
                hasBackButton = false,
                hasActionButton = true,
                actionButtonResource = Res.drawable.settings_ic,
                onActionButtonClicked = { navigateToSettings() }
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "Workout Type",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.size(8.dp))

                LazyRow(
                    modifier = Modifier
                        .layout { measurable, constraints ->
                            val placable = measurable.measure(
                                constraints.copy(
                                    maxWidth = constraints.maxWidth + 48.dp.roundToPx()
                                )
                            )
                            layout(placable.width, placable.height) {
                                placable.place(0, 0)
                            }

                        }
                ) {
                    val workoutModes = WorkoutMode.entries.toTypedArray()
                    items(
                        items = workoutModes,
                        key = { it.name },
                        contentType = { it.name }
                    ) { mode ->

                        Spacer(Modifier.size(if (workoutModes.first() == mode) 20.dp else 10.dp))

                        ModeCard(
                            modifier = Modifier
                                .height(120.dp),
                            mode = mode,
                            isSelected = selectedMode == mode,
                            onClick = { mode ->
                                selectedMode = mode
                                if (selectedMode != WorkoutMode.CUSTOM) {
                                    roundDuration = mode.roundDuration
                                    restDuration = mode.restDuration
                                    totalRounds = mode.rounds
                                }
                            },
                            emojiSize = 24.sp,
                            titleSize = 18.sp,
                            descriptionSize = 12.sp
                        )
                        if (workoutModes.last() == mode) {
                            Spacer(Modifier.size(12.dp))
                        }


                    }
                }

                Spacer(Modifier.size(8.dp))

                Text(
                    text = "Round Settings",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.size(12.dp))

                TimerSetter(
                    title = "Round Duration",
                    timeInMillis = roundDuration,
                    onPlusClicked = {
                        roundDuration = roundDuration + 30000
                        selectedMode = WorkoutMode.CUSTOM
                    },
                    onMinusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        if ((roundDuration - 30000 < 0).not()) {
                            roundDuration = roundDuration - 30000
                        }
                    },
                )

                Spacer(Modifier.size(12.dp))

                TimerSetter(
                    title = "Rest Duration",
                    timeInMillis = restDuration,
                    onPlusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        restDuration = restDuration + 30000
                    },
                    onMinusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        if ((restDuration - 30000 < 0).not()) {
                            restDuration = restDuration - 30000
                        }
                    },
                )

                Spacer(Modifier.size(12.dp))

                RoundNumberPicker(
                    title = "Rounds Number",
                    rounds = totalRounds,
                    onRoundsChanged = { rounds ->
                        selectedMode = WorkoutMode.CUSTOM
                        totalRounds = rounds
                    },
                    minRounds = 1,
                    maxRounds = 12,
                )

                Spacer(Modifier.size(12.dp))


                StartButton(
                    modifier = Modifier
                        .onVisibilityChanged(
                            callback = { isVisible ->
                                isFabVisible = !isVisible
                            }
                        )
                ) {
                    onStartWorkout(TimerSettings(roundDuration, restDuration, totalRounds))
                }

                Spacer(Modifier.size(72.dp))

            }


        }
    }

}

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {


    val titleGradientBrush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        start = Offset(0f, 0f), // Top-left corner
        end = Offset(1000f, 1000f), // Bottom-right corner, approximates 135deg
        tileMode = TileMode.Clamp
    )

    Button(
        modifier = modifier
            .height(60.dp)
            .background(brush = titleGradientBrush, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        Text(
            text = "Start Workout",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W800
        )
    }
}

@Preview
@Composable
private fun ConfigScreenPrev() {

    MaterialTheme(colorScheme = IceColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}