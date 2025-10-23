package com.cromulent.box_timer.presentation.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cromulent.box_timer.core.util.FontManager
import com.cromulent.box_timer.domain.AppLanguage
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BoxTimerProTheme(
    settingsViewModel: SettingsViewModel = koinViewModel(),
    content: @Composable () -> Unit
) {

    val settings by settingsViewModel.settings.collectAsStateWithLifecycle(null)

    var isFirstLoad by remember { mutableStateOf(true) }

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

    val backgroundGradientBrush = Brush.linearGradient(
        colors = listOf(
            animatedColorScheme.background,
            animatedColorScheme.surface,
            animatedColorScheme.primaryContainer
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f),
        tileMode = TileMode.Clamp
    )

    // Get the current language for font selection
    val currentLanguage = settings?.selectedLanguage ?: AppLanguage.SYSTEM
    val fontManager = remember { FontManager() }
    val customFontFamily = remember(currentLanguage) {
        fontManager.getFontFamily(currentLanguage)
    }

    val typography = if (customFontFamily != null) {
        Typography().copy(
            displayLarge = Typography().displayLarge.copy(fontFamily = customFontFamily),
            displayMedium = Typography().displayMedium.copy(fontFamily = customFontFamily),
            displaySmall = Typography().displaySmall.copy(fontFamily = customFontFamily),
            headlineLarge = Typography().headlineLarge.copy(fontFamily = customFontFamily),
            headlineMedium = Typography().headlineMedium.copy(fontFamily = customFontFamily),
            headlineSmall = Typography().headlineSmall.copy(fontFamily = customFontFamily),
            titleLarge = Typography().titleLarge.copy(fontFamily = customFontFamily),
            titleMedium = Typography().titleMedium.copy(fontFamily = customFontFamily),
            titleSmall = Typography().titleSmall.copy(fontFamily = customFontFamily),
            bodyLarge = Typography().bodyLarge.copy(fontFamily = customFontFamily),
            bodyMedium = Typography().bodyMedium.copy(fontFamily = customFontFamily),
            bodySmall = Typography().bodySmall.copy(fontFamily = customFontFamily),
            labelLarge = Typography().labelLarge.copy(fontFamily = customFontFamily),
            labelMedium = Typography().labelMedium.copy(fontFamily = customFontFamily),
            labelSmall = Typography().labelSmall.copy(fontFamily = customFontFamily)
        )
    } else {
        Typography()
    }

    MaterialTheme(
        colorScheme = animatedColorScheme,
        typography = typography
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


@Composable
fun BoxTimerProThemePrv(
    colorScheme: ColorScheme,
    content: @Composable () -> Unit
) {


    MaterialTheme(
        colorScheme = colorScheme
    ) {
        val backgroundGradientBrush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.primaryContainer
            ),
            start = Offset(0f, 0f),
            end = Offset(1000f, 1000f),
            tileMode = TileMode.Clamp
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