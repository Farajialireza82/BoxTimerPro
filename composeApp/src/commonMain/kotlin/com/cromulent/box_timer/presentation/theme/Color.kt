package com.cromulent.box_timer.presentation.theme

import androidx.compose.material3.darkColorScheme
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
val FireColorScheme = darkColorScheme(
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
    onSurface = GoldenSun,
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

// ICE THEME
val IceColorScheme = darkColorScheme(
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

// VENOM THEME
val VenomColorScheme = darkColorScheme(
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
    onSurface = Color(0xFFCCFF00),
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

// ROYAL THEME
val RoyalColorScheme = darkColorScheme(
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
    onSurface = Color(0xFFBDB8C5),
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

// SHADOW THEME
val ShadowColorScheme = darkColorScheme(
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
    onSurface = Color(0xFFC5B8B8),
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

// STEEL THEME
val SteelColorScheme = darkColorScheme(
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
    onSurface = Color(0xFFE0E0E0),
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


val SunsetColorScheme = darkColorScheme(
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
    onSurface = Color(0xFFFFD93D),
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
val OceanColorScheme = darkColorScheme(
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
    onSurface = Color(0xFF6DD5FA),
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
val NeonColorScheme = darkColorScheme(
    primary = Color(0xFFFF10F0),
    onPrimary = Color(0xFF0a0a0a),
    primaryContainer = Color(0xFF2d1d2d),
    onPrimaryContainer = Color(0xFFFF10F0),

    secondary = Color(0xFF00FFF0),
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
    onSurface = Color(0xFFFF10F0),
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
val ForestColorScheme = darkColorScheme(
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
    onSurface = Color(0xFF81C784),
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
val MidnightColorScheme = darkColorScheme(
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
    onSurface = Color(0xFF8FA5FF),
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
val PrincessPinkColorScheme = darkColorScheme(
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
    onSurface = Color(0xFFFFB6D9),
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


val colorSchemes = listOf(
    ColorSchemeDTO("ice" ,IceColorScheme),
    ColorSchemeDTO("fire" ,FireColorScheme),
    ColorSchemeDTO("venom" ,VenomColorScheme),
    ColorSchemeDTO("royal" ,RoyalColorScheme),
    ColorSchemeDTO("shadow" ,ShadowColorScheme),
    ColorSchemeDTO("steel" ,SteelColorScheme),
    ColorSchemeDTO("sunset" ,SunsetColorScheme),
    ColorSchemeDTO("ocean" ,OceanColorScheme),
    ColorSchemeDTO("neon" ,NeonColorScheme),
    ColorSchemeDTO("forest" ,ForestColorScheme),
    ColorSchemeDTO("midnight" ,MidnightColorScheme),
    ColorSchemeDTO("princess" ,PrincessPinkColorScheme),

)