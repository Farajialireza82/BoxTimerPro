package com.cromulent.box_timer.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.cromulent.box_timer.data.ColorSchemeDTO

val NightSkyBlack = Color(0xFF0a0a0a)
val StormCloudGray = Color(0xFF1a1a1a)
val AutumnBark = Color(0xFF2a1810)
val SootyGray = Color(0xFF0f0f0f)
val CoffeeBeanBrown = Color(0xFF2d1b10)
val AmberGlow = Color(0xFFF7931E)
val GoldenSun = Color(0xFFFFD700)
val SubtitleColor = Color(0xFFBABABA)
val SecondarySubtitleColor = Color(0xFF353130)
val CoralMist = Color(0xFF2b1d18)
val CoralHaze = Color(0xFFff6b35)
val FruitOrange = Color(0xFFFC5600)

// ICE THEME
val IceDarkColorScheme = darkColorScheme(
    primary = Color(0xFF4ECDC4),
    onPrimary = Color(0xFF0a0e1a),
    primaryContainer = Color(0xFF1a2d3d),
    onPrimaryContainer = Color(0xFFE0F7FA),

    secondary = Color(0xFF44A8F0),
    onSecondary = Color(0xFF0a0e1a),
    secondaryContainer = Color(0xFF1e3a47),
    onSecondaryContainer = Color(0xFFB0BEC5),

    tertiary = Color(0xFF00B8D4),
    onTertiary = Color(0xFF0a0e1a),
    tertiaryContainer = Color(0xFF102a3a),
    onTertiaryContainer = Color(0xFFE0F7FA),

    background = Color(0xFF0f1419),
    onBackground = Color(0xFFE0F7FA),

    surface = Color(0xFF1a1e2a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF1a2d3d),
    onSurfaceVariant = Color(0xFF1e3a47),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a0e1a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF4ECDC4),
    outlineVariant = Color(0xFF1e3a47),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF4ECDC4),
)
val IceLightColorScheme = lightColorScheme(
    primary = Color(0xFF00ACC1),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB2EBF2),
    onPrimaryContainer = Color(0xFF002F35),

    secondary = Color(0xFF0097A7),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFB2DFDB),
    onSecondaryContainer = Color(0xFF003D40),

    tertiary = Color(0xFF26C6DA),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE0F7FA),
    onTertiaryContainer = Color(0xFF004D54),

    background = Color(0xFFF0F8FF),
    onBackground = Color(0xFF001F24),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF001F24),
    surfaceVariant = Color(0xFFE0F2F1),
    onSurfaceVariant = Color(0xFF3F4E52),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF00ACC1),
    outlineVariant = Color(0xFFB2DFDB),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF00ACC1),

    inverseSurface = Color(0xFF001F24),
    inverseOnSurface = Color(0xFFE0F7FA),
    inversePrimary = Color(0xFF4DD0E1),
)

// FIRE THEME
val FireDarkColorScheme = darkColorScheme(
    primary = AmberGlow,
    onPrimary = NightSkyBlack,
    primaryContainer = CoffeeBeanBrown,
    onPrimaryContainer = GoldenSun,

    secondary = CoralHaze,
    onSecondary = NightSkyBlack,
    secondaryContainer = SecondarySubtitleColor,
    onSecondaryContainer = SubtitleColor,

    tertiary = FruitOrange,
    onTertiary = NightSkyBlack,
    tertiaryContainer = AutumnBark,
    onTertiaryContainer = GoldenSun,

    background = SootyGray,
    onBackground = GoldenSun,

    surface = StormCloudGray,
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = CoralMist,
    onSurfaceVariant = SecondarySubtitleColor,

    error = Color(0xFFFF6B6B),
    onError = NightSkyBlack,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = AmberGlow,
    outlineVariant = SecondarySubtitleColor,
    scrim = Color(0xFF000000),

    surfaceTint = AmberGlow,
)
val FireLightColorScheme = lightColorScheme(
    primary = AmberGlow, // Retains primary color
    onPrimary = GoldenSun, // Original onSurface/onBackground
    primaryContainer = Color(0xFFFFB74D), // Lighter, more pastel version of primary
    onPrimaryContainer = NightSkyBlack, // Original onPrimary

    secondary = CoralHaze,
    onSecondary = GoldenSun,
    secondaryContainer = Color(0xFFFFD1A8), // Lighter secondary container
    onSecondaryContainer = NightSkyBlack,

    tertiary = FruitOrange,
    onTertiary = GoldenSun,
    tertiaryContainer = Color(0xFFFFCC80), // Lighter tertiary container
    onTertiaryContainer = NightSkyBlack,

    background = Color(0xFFFFFFFF), // White background
    onBackground = NightSkyBlack, // Original onPrimary (dark text)

    surface = Color(0xFFFFFFFF), // White surface
    onSurface = NightSkyBlack, // Original onPrimary (dark text)
    surfaceVariant = Color(0xFFF0F0F0), // Light gray
    onSurfaceVariant = SecondarySubtitleColor,

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = AmberGlow,
    outlineVariant = Color(0xFFC5B8B0),
    scrim = Color(0xFF000000),

    surfaceTint = AmberGlow,
)


// VENOM THEME
val VenomDarkColorScheme = darkColorScheme(
    primary = Color(0xFF7FFF00),
    onPrimary = Color(0xFF0a140a),
    primaryContainer = Color(0xFF1d2a1b),
    onPrimaryContainer = Color(0xFFCCFF00),

    secondary = Color(0xFF32CD32),
    onSecondary = Color(0xFF0a140a),
    secondaryContainer = Color(0xFF253025),
    onSecondaryContainer = Color(0xFFB8C5B8),

    tertiary = Color(0xFF39FF14),
    onTertiary = Color(0xFF0a140a),
    tertiaryContainer = Color(0xFF1a2a10),
    onTertiaryContainer = Color(0xFFCCFF00),

    background = Color(0xFF0f190f),
    onBackground = Color(0xFFCCFF00),

    surface = Color(0xFF1a2a1a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF1d2a1b),
    onSurfaceVariant = Color(0xFFB8C5B8),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a140a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF7FFF00),
    outlineVariant = Color(0xFF253025),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF7FFF00),
)
val VenomLightColorScheme = lightColorScheme(
    primary = Color(0xFF00C853), // Slightly adjusted dark shade of the original primary
    onPrimary = Color(0xFFFFFFFF), // White text on dark primary
    primaryContainer = Color(0xFF69F0AE), // Light, high-energy green
    onPrimaryContainer = Color(0xFF0a140a), // Original onPrimary (dark text)

    secondary = Color(0xFF32CD32),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC8E6C9), // Light secondary container
    onSecondaryContainer = Color(0xFF0a140a),

    tertiary = Color(0xFF00E676), // Bright tertiary
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFCCFF00), // Original onPrimaryContainer
    onTertiaryContainer = Color(0xFF0a140a),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a140a), // Original onPrimary (dark text)

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a140a),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF253025),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF00C853),
    outlineVariant = Color(0xFFB8C5B8),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF00C853),
)

// ROYAL THEME
val RoyalDarkColorScheme = darkColorScheme(
    primary = Color(0xFFDA22FF),
    onPrimary = Color(0xFF0a0a14),
    primaryContainer = Color(0xFF251d2d),
    onPrimaryContainer = Color(0xFFB026FF),

    secondary = Color(0xFF9733EE),
    onSecondary = Color(0xFF0a0a14),
    secondaryContainer = Color(0xFF2a253a),
    onSecondaryContainer = Color(0xFFBDB8C5),

    tertiary = Color(0xFF667EEA),
    onTertiary = Color(0xFF0a0a14),
    tertiaryContainer = Color(0xFF1a102a),
    onTertiaryContainer = Color(0xFFBDB8C5),

    background = Color(0xFF0f0f19),
    onBackground = Color(0xFFBDB8C5),

    surface = Color(0xFF1a1a2a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF251d2d),
    onSurfaceVariant = Color(0xFFBDB8C5),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a0a14),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFDA22FF),
    outlineVariant = Color(0xFF2a253a),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFDA22FF),
)
val RoyalLightColorScheme = lightColorScheme(
    primary = Color(0xFF9C27B0), // Darker, more grounded violet
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE1BEE7), // Light lavender
    onPrimaryContainer = Color(0xFF0a0a14), // Original onPrimary (dark text)

    secondary = Color(0xFF7B1FA2), // Darker secondary
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD1C4E9), // Light secondary container
    onSecondaryContainer = Color(0xFF0a0a14),

    tertiary = Color(0xFF4D72D1), // Darker blue
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFC5CAE9), // Light tertiary container
    onTertiaryContainer = Color(0xFF0a0a14),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a0a14),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a0a14),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF2a253a),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF9C27B0),
    outlineVariant = Color(0xFFBDB8C5),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF9C27B0),
)

// SHADOW THEME
val ShadowDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF0844),
    onPrimary = Color(0xFF140a0a),
    primaryContainer = Color(0xFF2a1d1d),
    onPrimaryContainer = Color(0xFFFF3030),

    secondary = Color(0xFFCC0033),
    onSecondary = Color(0xFF140a0a),
    secondaryContainer = Color(0xFF3a2525),
    onSecondaryContainer = Color(0xFFC5B8B8),

    tertiary = Color(0xFF990022),
    onTertiary = Color(0xFF140a0a),
    tertiaryContainer = Color(0xFF2a1010),
    onTertiaryContainer = Color(0xFFC5B8B8),

    background = Color(0xFF190f0f),
    onBackground = Color(0xFFC5B8B8),

    surface = Color(0xFF2a1a1a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF2a1d1d),
    onSurfaceVariant = Color(0xFFC5B8B8),

    error = Color(0xFFFF0844),
    onError = Color(0xFF140a0a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFFF0844),
    outlineVariant = Color(0xFF3a2525),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFFF0844),
)
val ShadowLightColorScheme = lightColorScheme(
    primary = Color(0xFFD32F2F), // Darker, less blinding red
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFCDD2), // Pale red/pink
    onPrimaryContainer = Color(0xFF140a0a), // Original onPrimary (dark text)

    secondary = Color(0xFFC62828),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFCCBC), // Light coral/secondary container
    onSecondaryContainer = Color(0xFF140a0a),

    tertiary = Color(0xFFB71C1C),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFEF9A9A), // Light tertiary container
    onTertiaryContainer = Color(0xFF140a0a),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF140a0a),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF140a0a),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF3a2525),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFFD32F2F),
    outlineVariant = Color(0xFFC5B8B8),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFD32F2F),
)

// STEEL THEME
val SteelDarkColorScheme = darkColorScheme(
    primary = Color(0xFFE0E0E0),
    onPrimary = Color(0xFF0a0a0a),
    primaryContainer = Color(0xFF1d1d1d),
    onPrimaryContainer = Color(0xFFE0E0E0),

    secondary = Color(0xFF9E9E9E),
    onSecondary = Color(0xFF0a0a0a),
    secondaryContainer = Color(0xFF2a2a2a),
    onSecondaryContainer = Color(0xFFB8B8B8),

    tertiary = Color(0xFF616161),
    onTertiary = Color(0xFFE0E0E0),
    tertiaryContainer = Color(0xFF1a1a1a),
    onTertiaryContainer = Color(0xFFB8B8B8),

    background = Color(0xFF0f0f0f),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1a1a1a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF1d1d1d),
    onSurfaceVariant = Color(0xFFB8B8B8),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a0a0a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFF2a2a2a),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFE0E0E0),
)
val SteelLightColorScheme = lightColorScheme(
    primary = Color(0xFF616161), // Dark gray
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE0E0E0), // Original primary (light gray)
    onPrimaryContainer = Color(0xFF0a0a0a), // Original onPrimary (dark text)

    secondary = Color(0xFF757575), // Darker secondary
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFBDBDBD), // Lighter gray
    onSecondaryContainer = Color(0xFF0a0a0a),

    tertiary = Color(0xFF9E9E9E), // Light tertiary
    onTertiary = Color(0xFF0a0a0a), // Dark text on light tertiary
    tertiaryContainer = Color(0xFFF5F5F5), // Very light gray
    onTertiaryContainer = Color(0xFF0a0a0a),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a0a0a),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a0a0a),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF2a2a2a),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF757575),
    outlineVariant = Color(0xFFBDBDBD),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF616161),
)


val SunsetDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF6B6B),
    onPrimary = Color(0xFF1a0a0a),
    primaryContainer = Color(0xFF2d1d1d),
    onPrimaryContainer = Color(0xFFFFB347),

    secondary = Color(0xFFFF8C42),
    onSecondary = Color(0xFF1a0a0a),
    secondaryContainer = Color(0xFF3a2520),
    onSecondaryContainer = Color(0xFFC5B0A8),

    tertiary = Color(0xFFFFD93D),
    onTertiary = Color(0xFF1a0a0a),
    tertiaryContainer = Color(0xFF2a1d10),
    onTertiaryContainer = Color(0xFFC5B8A8),

    background = Color(0xFF190f0f),
    onBackground = Color(0xFFFFD93D),

    surface = Color(0xFF2a1a1a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF2d1d1d),
    onSurfaceVariant = Color(0xFFC5B0A8),

    error = Color(0xFFFF4757),
    onError = Color(0xFF1a0a0a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFFF6B6B),
    outlineVariant = Color(0xFF3a2520),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFFF6B6B),
)
val SunsetLightColorScheme = lightColorScheme(
    primary = Color(0xFFD32F2F), // Darker red-orange for contrast
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFCCBC), // Light salmon
    onPrimaryContainer = Color(0xFF1a0a0a), // Original onPrimary (dark text)

    secondary = Color(0xFFF57C00), // Darker orange
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFE0B2), // Light peach
    onSecondaryContainer = Color(0xFF1a0a0a),

    tertiary = Color(0xFFFFA000), // Darker yellow-orange
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFF9C4), // Lightest yellow
    onTertiaryContainer = Color(0xFF1a0a0a),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1a0a0a),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1a0a0a),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF3a2520),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFFD32F2F),
    outlineVariant = Color(0xFFC5B0A8),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFD32F2F),
)

val OceanDarkColorScheme = darkColorScheme(
    primary = Color(0xFF0077BE),
    onPrimary = Color(0xFF0a0e14),
    primaryContainer = Color(0xFF1d2a3d),
    onPrimaryContainer = Color(0xFF6DD5FA),

    secondary = Color(0xFF2E8B57),
    onSecondary = Color(0xFF0a140e),
    secondaryContainer = Color(0xFF1e3a2f),
    onSecondaryContainer = Color(0xFFA8C5B8),

    tertiary = Color(0xFF20B2AA),
    onTertiary = Color(0xFF0a1414),
    tertiaryContainer = Color(0xFF102a2a),
    onTertiaryContainer = Color(0xFFA8C5C5),

    background = Color(0xFF0f1419),
    onBackground = Color(0xFF6DD5FA),

    surface = Color(0xFF1a1e2a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF1d2a3d),
    onSurfaceVariant = Color(0xFFA8B8C5),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a0e14),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF0077BE),
    outlineVariant = Color(0xFF1e3a2f),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF0077BE),
)
val OceanLightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2), // Darker blue
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFBBDEFB), // Lightest blue
    onPrimaryContainer = Color(0xFF0a0e14), // Original onPrimary (dark text)

    secondary = Color(0xFF388E3C), // Darker green
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFC8E6C9), // Lightest green
    onSecondaryContainer = Color(0xFF0a140e),

    tertiary = Color(0xFF009688), // Teal
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFB2DFDB), // Light teal
    onTertiaryContainer = Color(0xFF0a1414),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a0e14),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a0e14),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF1d2a3d),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF1976D2),
    outlineVariant = Color(0xFFA8B8C5),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF1976D2),
)

val NeonDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF10F0),
    onPrimary = Color(0xFF0a0a0a),
    primaryContainer = Color(0xFF2d1d2d),
    onPrimaryContainer = Color(0xFFFF10F0),

    secondary = Color(0xFF00B0A3),
    onSecondary = Color(0xFF0a0a0a),
    secondaryContainer = Color(0xFF1d2d2d),
    onSecondaryContainer = Color(0xFFB8C5C5),

    tertiary = Color(0xFFFFFF00),
    onTertiary = Color(0xFF0a0a0a),
    tertiaryContainer = Color(0xFF2d2d1d),
    onTertiaryContainer = Color(0xFFC5C5B8),

    background = Color(0xFF0f0f0f),
    onBackground = Color(0xFFFF10F0),

    surface = Color(0xFF1a1a1a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF2d1d2d),
    onSurfaceVariant = Color(0xFFC5B8C5),

    error = Color(0xFFFF0055),
    onError = Color(0xFF0a0a0a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFFF10F0),
    outlineVariant = Color(0xFF2d1d2d),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFFF10F0),
)
val NeonLightColorScheme = lightColorScheme(
    primary = Color(0xFF880E4F), // Darker, rich magenta for contrast
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFF80AB), // Bright pink/magenta
    onPrimaryContainer = Color(0xFF0a0a0a), // Original onPrimary (dark text)

    secondary = Color(0xFF00BCD4), // Teal
    onSecondary = Color(0xFF0a0a0a), // Dark text on light secondary
    secondaryContainer = Color(0xFFB2EBF2), // Lightest cyan
    onSecondaryContainer = Color(0xFF0a0a0a),

    tertiary = Color(0xFFFBC02D), // Darker yellow
    onTertiary = Color(0xFF0a0a0a),
    tertiaryContainer = Color(0xFFFFF9C4), // Lightest yellow
    onTertiaryContainer = Color(0xFF0a0a0a),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a0a0a),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a0a0a),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF2d1d2d),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF880E4F),
    outlineVariant = Color(0xFFC5B8C5),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF880E4F),
)

val ForestDarkColorScheme = darkColorScheme(
    primary = Color(0xFF4CAF50),
    onPrimary = Color(0xFF0a140a),
    primaryContainer = Color(0xFF1d2a1d),
    onPrimaryContainer = Color(0xFF81C784),

    secondary = Color(0xFF8BC34A),
    onSecondary = Color(0xFF0a140a),
    secondaryContainer = Color(0xFF253025),
    onSecondaryContainer = Color(0xFFB8C5A8),

    tertiary = Color(0xFF66BB6A),
    onTertiary = Color(0xFF0a140a),
    tertiaryContainer = Color(0xFF1a2a1a),
    onTertiaryContainer = Color(0xFFB8C5B8),

    background = Color(0xFF0f190f),
    onBackground = Color(0xFF81C784),

    surface = Color(0xFF1a2a1a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF1d2a1d),
    onSurfaceVariant = Color(0xFFB8C5A8),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a140a),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF4CAF50),
    outlineVariant = Color(0xFF253025),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF4CAF50),
)
val ForestLightColorScheme = lightColorScheme(
    primary = Color(0xFF388E3C), // Darker, rich green
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC8E6C9), // Lightest green
    onPrimaryContainer = Color(0xFF0a140a), // Original onPrimary (dark text)

    secondary = Color(0xFF689F38), // Darker lime green
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDCEDC8), // Light secondary green
    onSecondaryContainer = Color(0xFF0a140a),

    tertiary = Color(0xFF81C784), // Light green
    onTertiary = Color(0xFF0a140a), // Dark text on light tertiary
    tertiaryContainer = Color(0xFFE8F5E9), // Very light green
    onTertiaryContainer = Color(0xFF0a140a),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a140a),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a140a),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF1d2a1d),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF388E3C),
    outlineVariant = Color(0xFFB8C5A8),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF388E3C),
)

val MidnightDarkColorScheme = darkColorScheme(
    primary = Color(0xFF5B7FFF),
    onPrimary = Color(0xFF0a0a14),
    primaryContainer = Color(0xFF1d1d2d),
    onPrimaryContainer = Color(0xFF8FA5FF),

    secondary = Color(0xFF3F51B5),
    onSecondary = Color(0xFF0a0a14),
    secondaryContainer = Color(0xFF25253a),
    onSecondaryContainer = Color(0xFFB0B0C5),

    tertiary = Color(0xFF7986CB),
    onTertiary = Color(0xFF0a0a14),
    tertiaryContainer = Color(0xFF1a1a2a),
    onTertiaryContainer = Color(0xFFB8B8C5),

    background = Color(0xFF0f0f19),
    onBackground = Color(0xFF8FA5FF),

    surface = Color(0xFF1a1a2a),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF1d1d2d),
    onSurfaceVariant = Color(0xFFB0B0C5),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0a0a14),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF5B7FFF),
    outlineVariant = Color(0xFF25253a),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF5B7FFF),
)
val MidnightLightColorScheme = lightColorScheme(
    primary = Color(0xFF304FFE), // Rich, deep blue
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC5CAE9), // Light blue-gray
    onPrimaryContainer = Color(0xFF0a0a14), // Original onPrimary (dark text)

    secondary = Color(0xFF5C6BC0), // Medium blue-gray
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8EAF6), // Very light secondary blue-gray
    onSecondaryContainer = Color(0xFF0a0a14),

    tertiary = Color(0xFF7986CB), // Light tertiary blue
    onTertiary = Color(0xFF0a0a14), // Dark text on light tertiary
    tertiaryContainer = Color(0xFFF0F4C3), // Pale yellow-ish for subtle contrast
    onTertiaryContainer = Color(0xFF0a0a14),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0a0a14),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0a0a14),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF1d1d2d),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFF304FFE),
    outlineVariant = Color(0xFFB0B0C5),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF304FFE),
)

val PrincessPinkDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF69B4),
    onPrimary = Color(0xFF140a0f),
    primaryContainer = Color(0xFF2d1d25),
    onPrimaryContainer = Color(0xFFFFB6D9),

    secondary = Color(0xFFFF1493),
    onSecondary = Color(0xFF140a0f),
    secondaryContainer = Color(0xFF3a2530),
    onSecondaryContainer = Color(0xFFC5A8B8),

    tertiary = Color(0xFFFF85C1),
    onTertiary = Color(0xFF140a0f),
    tertiaryContainer = Color(0xFF2a1a25),
    onTertiaryContainer = Color(0xFFC5B0BD),

    background = Color(0xFF190f14),
    onBackground = Color(0xFFFFB6D9),

    surface = Color(0xFF2a1a25),
    onSurface = Color(0xFFE0F7FA),
    surfaceVariant = Color(0xFF2d1d25),
    onSurfaceVariant = Color(0xFFC5A8B8),

    error = Color(0xFFFF0066),
    onError = Color(0xFF140a0f),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFFF69B4),
    outlineVariant = Color(0xFF3a2530),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFFF69B4),
)
val PrincessPinkLightColorScheme = lightColorScheme(
    primary = Color(0xFFD81B60), // Darker, rich rose pink
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFF80AB), // Bright pink/magenta
    onPrimaryContainer = Color(0xFF140a0f), // Original onPrimary (dark text)

    secondary = Color(0xFFC2185B), // Darker secondary red-pink
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFCDD2), // Lightest red-pink
    onSecondaryContainer = Color(0xFF140a0f),

    tertiary = Color(0xFFE91E63), // Primary red-pink
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFF8BBD0), // Light tertiary pink
    onTertiaryContainer = Color(0xFF140a0f),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF140a0f),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF140a0f),
    surfaceVariant = Color(0xFFF0F0F0),
    onSurfaceVariant = Color(0xFF2d1d25),

    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    outline = Color(0xFFD81B60),
    outlineVariant = Color(0xFFC5A8B8),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFD81B60),
)

val colorSchemes = listOf(
    ColorSchemeDTO("ice", IceDarkColorScheme, IceLightColorScheme),
    ColorSchemeDTO("fire", FireDarkColorScheme, FireLightColorScheme),
    ColorSchemeDTO("venom", VenomDarkColorScheme, VenomLightColorScheme),
    ColorSchemeDTO("royal", RoyalDarkColorScheme, RoyalLightColorScheme),
    ColorSchemeDTO("shadow", ShadowDarkColorScheme, ShadowLightColorScheme),
    ColorSchemeDTO("steel", SteelDarkColorScheme, SteelLightColorScheme),
    ColorSchemeDTO("sunset", SunsetDarkColorScheme, SunsetLightColorScheme),
    ColorSchemeDTO("ocean", OceanDarkColorScheme, OceanLightColorScheme),
    ColorSchemeDTO("neon", NeonDarkColorScheme, NeonLightColorScheme),
    ColorSchemeDTO("forest", ForestDarkColorScheme, ForestLightColorScheme),
    ColorSchemeDTO("midnight", MidnightDarkColorScheme, MidnightLightColorScheme),
    ColorSchemeDTO("princess", PrincessPinkDarkColorScheme, PrincessPinkLightColorScheme),

    )