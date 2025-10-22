package com.cromulent.box_timer.presentation.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import com.cromulent.box_timer.presentation.timer_screen.TimerState
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus
import com.cromulent.box_timer.presentation.timer_screen.isInActiveState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BoxTimerProTheme(
    settingsViewModel: SettingsViewModel = koinViewModel(),
    timerState: TimerState? = null,
    content: @Composable () -> Unit
) {

    val settings by settingsViewModel.settings.collectAsStateWithLifecycle(null)

    var isFirstLoad by rememberSaveable { mutableStateOf(true) }

    val targetColorSchemeDTO = colorSchemes
        .firstOrNull { it.id == settings?.colorSchemeId } ?: colorSchemes.first()

    val targetColorScheme =
        if (settings?.isDarkMode == true) targetColorSchemeDTO.darkColorScheme else targetColorSchemeDTO.lightColorScheme

    // Skip animation on first load
    val animatedColorScheme = if (isFirstLoad) {
        LaunchedEffect(Unit) {
            isFirstLoad = false
        }
        targetColorScheme
    } else {
        targetColorScheme.animate()
    }

    // Dynamic gradient based on timer state - skip animations on first load
    val backgroundGradientBrush = createDynamicGradient(
        colorScheme = animatedColorScheme,
        timerState = if (isFirstLoad) null else timerState,
        isFirstLoad = isFirstLoad
    )

    MaterialTheme(
        colorScheme = animatedColorScheme
    ) {
        Box(
            modifier = Modifier
                .background(backgroundGradientBrush)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

/**
 * Creates a subtle dynamic gradient that enhances the background with primary color based on timer progress
 */
@Composable
fun createDynamicGradient(
    colorScheme: ColorScheme,
    timerState: TimerState?,
    isFirstLoad: Boolean = false
): Brush {
    // Default gradient colors with darker background
    val baseColors = listOf(
        colorScheme.background.copy(alpha = 0.85f), // Darker default background
        colorScheme.surface.copy(alpha = 0.9f),
        colorScheme.primaryContainer.copy(alpha = 0.8f)
    )

    // Enhanced colors based on timer state
    val enhancedColors = when {
        timerState?.timerStatus == TimerStatus.CountDown -> {
            // Countdown state - tertiary color for anticipation
            val countdownIntensity = 0.06f + (timerState.progress * 0.12f) // 6-18% tertiary tint

            listOf(
                // Blend background with tertiary color
                colorScheme.background.copy(alpha = 1f - countdownIntensity).let { bg ->
                    Color(
                        red = (bg.red * (1f - countdownIntensity) + colorScheme.tertiary.red * countdownIntensity),
                        green = (bg.green * (1f - countdownIntensity) + colorScheme.tertiary.green * countdownIntensity),
                        blue = (bg.blue * (1f - countdownIntensity) + colorScheme.tertiary.blue * countdownIntensity),
                        alpha = bg.alpha
                    )
                },
                // Blend surface with tertiary color
                colorScheme.surface.copy(alpha = 1f - countdownIntensity).let { surface ->
                    Color(
                        red = (surface.red * (1f - countdownIntensity) + colorScheme.tertiary.red * countdownIntensity),
                        green = (surface.green * (1f - countdownIntensity) + colorScheme.tertiary.green * countdownIntensity),
                        blue = (surface.blue * (1f - countdownIntensity) + colorScheme.tertiary.blue * countdownIntensity),
                        alpha = surface.alpha
                    )
                },
                // Enhance tertiary container
                colorScheme.tertiaryContainer.copy(alpha = 1f + countdownIntensity)
            )
        }
        timerState?.timerStatus == TimerStatus.Resting -> {
            // Resting state - calming secondary color tint
            val restIntensity = 0.02f + (timerState.progress * 0.04f) // 2-6% secondary tint

            listOf(
                // Blend background with secondary color
                colorScheme.background.copy(alpha = 1f - restIntensity).let { bg ->
                    Color(
                        red = (bg.red * (1f - restIntensity) + colorScheme.secondary.red * restIntensity),
                        green = (bg.green * (1f - restIntensity) + colorScheme.secondary.green * restIntensity),
                        blue = (bg.blue * (1f - restIntensity) + colorScheme.secondary.blue * restIntensity),
                        alpha = bg.alpha
                    )
                },
                // Blend surface with secondary color
                colorScheme.surface.copy(alpha = 1f - restIntensity).let { surface ->
                    Color(
                        red = (surface.red * (1f - restIntensity) + colorScheme.secondary.red * restIntensity),
                        green = (surface.green * (1f - restIntensity) + colorScheme.secondary.green * restIntensity),
                        blue = (surface.blue * (1f - restIntensity) + colorScheme.secondary.blue * restIntensity),
                        alpha = surface.alpha
                    )
                },
                // Enhance secondary container
                colorScheme.secondaryContainer.copy(alpha = 1f + restIntensity)
            )
        }
        timerState != null && timerState.isInActiveState() && timerState.progress > 0f -> {
            // Running state - primary color enhancement
            val baseIntensity = 0.04f // Base 4% primary color when active
            val progressIntensity = timerState.progress * 0.10f // Additional 10% based on progress
            val primaryIntensity = (baseIntensity + progressIntensity).coerceAtMost(0.14f) // Max 14% total

            listOf(
                // Blend background with primary color
                colorScheme.background.copy(alpha = 1f - primaryIntensity).let { bg ->
                    Color(
                        red = (bg.red * (1f - primaryIntensity) + colorScheme.primary.red * primaryIntensity),
                        green = (bg.green * (1f - primaryIntensity) + colorScheme.primary.green * primaryIntensity),
                        blue = (bg.blue * (1f - primaryIntensity) + colorScheme.primary.blue * primaryIntensity),
                        alpha = bg.alpha
                    )
                },
                // Blend surface with primary color
                colorScheme.surface.copy(alpha = 1f - primaryIntensity).let { surface ->
                    Color(
                        red = (surface.red * (1f - primaryIntensity) + colorScheme.primary.red * primaryIntensity),
                        green = (surface.green * (1f - primaryIntensity) + colorScheme.primary.green * primaryIntensity),
                        blue = (surface.blue * (1f - primaryIntensity) + colorScheme.primary.blue * primaryIntensity),
                        alpha = surface.alpha
                    )
                },
                // Enhance primary container slightly
                colorScheme.primaryContainer.copy(alpha = 1f + primaryIntensity)
            )
        }
        else -> baseColors
    }

    // Skip animation on first load, animate gradient colors for smooth transitions afterwards
    val animatedColors = if (isFirstLoad) {
        enhancedColors
    } else {
        enhancedColors.map { color ->
            animateColorAsState(
                targetValue = color,
                animationSpec = tween(durationMillis = 600, delayMillis = 0)
            ).value
        }
    }

    return Brush.linearGradient(
        colors = animatedColors,
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f),
        tileMode = TileMode.Clamp
    )
}

@Composable
fun BoxTimerProThemePrv(
    colorScheme: ColorScheme,
    timerState: TimerState? = null,
    content: @Composable () -> Unit
) {


    MaterialTheme(
        colorScheme = colorScheme
    ) {
        val backgroundGradientBrush = createDynamicGradient(
            colorScheme = colorScheme,
            timerState = timerState
        )

        Box(
            modifier = Modifier
                .background(backgroundGradientBrush)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
fun ColorScheme.animate(animationDuration: Int = 400): ColorScheme {

    val spec = tween<Color>(durationMillis = animationDuration)

    val primary by animateColorAsState(targetValue = primary, animationSpec = spec)
    val onPrimary by animateColorAsState(targetValue = onPrimary, animationSpec = spec)
    val primaryContainer by animateColorAsState(
        targetValue = primaryContainer,
        animationSpec = spec
    )
    val onPrimaryContainer by animateColorAsState(
        targetValue = onPrimaryContainer,
        animationSpec = spec
    )

    val secondary by animateColorAsState(targetValue = secondary, animationSpec = spec)
    val onSecondary by animateColorAsState(targetValue = onSecondary, animationSpec = spec)

    val background by animateColorAsState(targetValue = background, animationSpec = spec)
    val onBackground by animateColorAsState(targetValue = onBackground, animationSpec = spec)
    val surface by animateColorAsState(targetValue = surface, animationSpec = spec)
    val onSurface by animateColorAsState(targetValue = onSurface, animationSpec = spec)
    val surfaceContainer by animateColorAsState(
        targetValue = surfaceContainer,
        animationSpec = spec
    )

    return copy(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceContainer = surfaceContainer,
    )
}