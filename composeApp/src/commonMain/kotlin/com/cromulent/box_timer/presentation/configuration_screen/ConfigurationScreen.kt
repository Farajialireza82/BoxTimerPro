package com.cromulent.box_timer.presentation.configuration_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import boxtimerpro.composeapp.generated.resources.configure_your_workout
import boxtimerpro.composeapp.generated.resources.presets
import boxtimerpro.composeapp.generated.resources.rest_duration
import boxtimerpro.composeapp.generated.resources.round_duration
import boxtimerpro.composeapp.generated.resources.round_number
import boxtimerpro.composeapp.generated.resources.round_settings
import boxtimerpro.composeapp.generated.resources.settings_ic
import boxtimerpro.composeapp.generated.resources.start_workout
import boxtimerpro.composeapp.generated.resources.ten_seconds
import com.cromulent.box_timer.presentation.configuration_screen.util.WorkoutMode
import com.cromulent.box_timer.core.util.toWorkoutMode
import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.configuration_screen.components.ModeCard
import com.cromulent.box_timer.presentation.configuration_screen.components.RoundNumberPicker
import com.cromulent.box_timer.presentation.configuration_screen.components.TimerSetter
import com.cromulent.box_timer.presentation.theme.BoxTimerProThemePrv
import com.cromulent.box_timer.presentation.theme.FireDarkColorScheme
import com.cromulent.box_timer.presentation.theme.FireLightColorScheme
import com.cromulent.box_timer.presentation.theme.ForestDarkColorScheme
import com.cromulent.box_timer.presentation.theme.ForestLightColorScheme
import com.cromulent.box_timer.presentation.theme.IceDarkColorScheme
import com.cromulent.box_timer.presentation.theme.IceLightColorScheme
import com.cromulent.box_timer.presentation.theme.MidnightDarkColorScheme
import com.cromulent.box_timer.presentation.theme.MidnightLightColorScheme
import com.cromulent.box_timer.presentation.theme.NeonDarkColorScheme
import com.cromulent.box_timer.presentation.theme.NeonLightColorScheme
import com.cromulent.box_timer.presentation.theme.OceanDarkColorScheme
import com.cromulent.box_timer.presentation.theme.OceanLightColorScheme
import com.cromulent.box_timer.presentation.theme.PrincessPinkDarkColorScheme
import com.cromulent.box_timer.presentation.theme.PrincessPinkLightColorScheme
import com.cromulent.box_timer.presentation.theme.RoyalDarkColorScheme
import com.cromulent.box_timer.presentation.theme.RoyalLightColorScheme
import com.cromulent.box_timer.presentation.theme.ShadowDarkColorScheme
import com.cromulent.box_timer.presentation.theme.ShadowLightColorScheme
import com.cromulent.box_timer.presentation.theme.SunsetDarkColorScheme
import com.cromulent.box_timer.presentation.theme.SunsetLightColorScheme
import com.cromulent.box_timer.presentation.theme.VenomDarkColorScheme
import com.cromulent.box_timer.presentation.theme.VenomLightColorScheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Play
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
internal fun ConfigurationScreen(
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
                            Text(stringResource(Res.string.start_workout))
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
                subtitle = stringResource(Res.string.configure_your_workout),
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
                    text = stringResource(Res.string.presets),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.size(14.dp))

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

                Spacer(Modifier.size(18.dp))

                Text(
                    text = stringResource(Res.string.round_settings),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.size(14.dp))

                TimerSetter(
                    title = stringResource(Res.string.round_duration),
                    timeInMillis = roundDuration,
                    isPlusButtonEnabled = true,
                    isMinusButtonEnabled = roundDuration - 10000 > 0,
                    timeUnitLabel = stringResource(Res.string.ten_seconds),
                    onPlusClicked = {
                        roundDuration = roundDuration + 10000
                        selectedMode = WorkoutMode.CUSTOM
                    },
                    onMinusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        roundDuration = roundDuration - 10000
                    },
                    onDoublePlusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        roundDuration = roundDuration + 60000
                    },
                    onDoubleMinusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        roundDuration = roundDuration - 60000
                    },
                    isDoublePlusEnabled = roundDuration + 60000 > 0,
                    isDoubleMinusEnabled = roundDuration - 60000 > 0
                )

                Spacer(Modifier.size(12.dp))

                TimerSetter(
                    title = stringResource(Res.string.rest_duration),
                    timeInMillis = restDuration,
                    isPlusButtonEnabled = true,
                    isMinusButtonEnabled = restDuration - 10000 > 0,
                    timeUnitLabel = stringResource(Res.string.ten_seconds),
                    onPlusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        restDuration = restDuration + 10000
                    },
                    onMinusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        restDuration = restDuration - 10000
                    },
                    onDoublePlusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        restDuration = restDuration + 60000
                    },
                    onDoubleMinusClicked = {
                        selectedMode = WorkoutMode.CUSTOM
                        restDuration = restDuration - 60000
                    },
                    isDoublePlusEnabled = restDuration + 60000 > 0,
                    isDoubleMinusEnabled = restDuration - 60000 > 0
                )

                Spacer(Modifier.size(12.dp))

                RoundNumberPicker(
                    title = stringResource(Res.string.round_number),
                    rounds = totalRounds,
                    onRoundsChanged = { rounds ->
                        selectedMode = WorkoutMode.CUSTOM
                        totalRounds = rounds
                    },
                    minRounds = 1,
                    maxRounds = Int.MAX_VALUE,
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
            text = stringResource(Res.string.start_workout),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W800
        )
    }
}

@Preview
@Composable
private fun IceDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = IceDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun IceLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = IceLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun FireDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = FireDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun FireLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = FireLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun VenomDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = VenomDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}
@Preview
@Composable
private fun VenomLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = VenomLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun RoyalDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = RoyalDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun RoyalLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = RoyalLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun ShadowDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = ShadowDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun ShadowLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = ShadowLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}


@Preview
@Composable
private fun SunsetDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = SunsetDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun SunsetLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = SunsetLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun OceanDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = OceanDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun OceanLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = OceanLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun NeonDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = NeonDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun NeonLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = NeonLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun ForestDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = ForestDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun ForestLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = ForestLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun MidnightDarkColorScheme() {

    BoxTimerProThemePrv(colorScheme = MidnightDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun MidnightLightColorScheme() {

    BoxTimerProThemePrv(colorScheme = MidnightLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun PrincessPinkDarkColorScheme() {

    BoxTimerProThemePrv (colorScheme = PrincessPinkDarkColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}

@Preview
@Composable
private fun PrincessPinkLightColorScheme() {

    BoxTimerProThemePrv (colorScheme = PrincessPinkLightColorScheme) {
        ConfigurationScreen(
            onStartWorkout = {},
            navigateToSettings = {}
        )

    }

}