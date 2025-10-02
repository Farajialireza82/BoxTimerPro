package com.cromulent.box_timer.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
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


val CrimsonDawnColorScheme = darkColorScheme(
    primary = Color(0xFFFF3B30), // Bright Red
    onPrimary = Color(0xFF1A0A0A),
    primaryContainer = Color(0xFF331515),
    onPrimaryContainer = Color(0xFFFF6666),

    secondary = Color(0xFFFF9500), // Deep Orange
    onSecondary = Color(0xFF1A0A0A),
    secondaryContainer = Color(0xFF3A2A1A),
    onSecondaryContainer = Color(0xFFC5B8B0),

    tertiary = Color(0xFFFFCC00), // Gold accent
    onTertiary = Color(0xFF1A0A0A),
    tertiaryContainer = Color(0xFF2A1A10),
    onTertiaryContainer = Color(0xFFC5B8B0),

    background = Color(0xFF0F0F0F),
    onBackground = Color(0xFFF0F0F0),

    surface = Color(0xFF1A1A1A),
    onSurface = Color(0xFFF0F0F0),
    surfaceVariant = Color(0xFF2A1D1D),
    onSurfaceVariant = Color(0xFFC5B8B0),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF1A0A0A),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFFF3B30),
    outlineVariant = Color(0xFF3A2A1A),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFFF3B30),
)

val DeepOceanColorScheme = darkColorScheme(
    primary = Color(0xFF00BFFF), // Deep Sky Blue
    onPrimary = Color(0xFF0A0A1A),
    primaryContainer = Color(0xFF1A2A3A),
    onPrimaryContainer = Color(0xFFA0E6FF),

    secondary = Color(0xFF40E0D0), // Turquoise
    onSecondary = Color(0xFF0A0A1A),
    secondaryContainer = Color(0xFF1E3A47),
    onSecondaryContainer = Color(0xFFB0BEC5),

    tertiary = Color(0xFF7B68EE), // Medium Slate Blue
    onTertiary = Color(0xFF0A0A1A),
    tertiaryContainer = Color(0xFF102A3A),
    onTertiaryContainer = Color(0xFFB0BEC5),

    background = Color(0xFF0F1419),
    onBackground = Color(0xFFA0E6FF),

    surface = Color(0xFF1A1E2A),
    onSurface = Color(0xFFA0E6FF),
    surfaceVariant = Color(0xFF1A2D3D),
    onSurfaceVariant = Color(0xFF1E3A47),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0A0A1A),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF00BFFF),
    outlineVariant = Color(0xFF1E3A47),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF00BFFF),
)

val DesertDuneColorScheme = darkColorScheme(
    primary = Color(0xFFB8860B), // Dark Goldenrod
    onPrimary = Color(0xFF1A1A0A),
    primaryContainer = Color(0xFF2A2A1A),
    onPrimaryContainer = Color(0xFFDDAA00),

    secondary = Color(0xFF8FBC8F), // Dark Sea Green
    onSecondary = Color(0xFF1A1A0A),
    secondaryContainer = Color(0xFF253025),
    onSecondaryContainer = Color(0xFFC5B8B8),

    tertiary = Color(0xFFCD853F), // Peru (Brown/Orange)
    onTertiary = Color(0xFF1A1A0A),
    tertiaryContainer = Color(0xFF2A1D10),
    onTertiaryContainer = Color(0xFFDDAA00),

    background = Color(0xFF14140F),
    onBackground = Color(0xFFDDCCAA),

    surface = Color(0xFF1F1F17),
    onSurface = Color(0xFFDDCCAA),
    surfaceVariant = Color(0xFF2A2A1A),
    onSurfaceVariant = Color(0xFF253025),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF1A1A0A),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFB8860B),
    outlineVariant = Color(0xFF253025),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFB8860B),
)

val NeonSynthColorScheme = darkColorScheme(
    primary = Color(0xFFFF69B4), // Hot Pink
    onPrimary = Color(0xFF1A0A1A),
    primaryContainer = Color(0xFF3A1A2A),
    onPrimaryContainer = Color(0xFFFFB3D9),

    secondary = Color(0xFF00FFFF), // Cyan
    onSecondary = Color(0xFF1A0A1A),
    secondaryContainer = Color(0xFF1A3A3A),
    onSecondaryContainer = Color(0xFFB8C5C5),

    tertiary = Color(0xFF6A5ACD), // Slate Blue
    onTertiary = Color(0xFF1A0A1A),
    tertiaryContainer = Color(0xFF2A1A3A),
    onTertiaryContainer = Color(0xFFB8C5C5),

    background = Color(0xFF0A0A0F),
    onBackground = Color(0xFFF0F0F5),

    surface = Color(0xFF14141A),
    onSurface = Color(0xFFF0F0F5),
    surfaceVariant = Color(0xFF3A1A2A),
    onSurfaceVariant = Color(0xFFB8C5C5),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF1A0A1A),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFFF69B4),
    outlineVariant = Color(0xFF1A3A3A),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFFF69B4),
)

val EmeraldForestColorScheme = darkColorScheme(
    primary = Color(0xFF3CB371), // Medium Sea Green
    onPrimary = Color(0xFF0A1A0A),
    primaryContainer = Color(0xFF1A3A1A),
    onPrimaryContainer = Color(0xFF66CC99),

    secondary = Color(0xFF556B2F), // Dark Olive Green
    onSecondary = Color(0xFF0A1A0A),
    secondaryContainer = Color(0xFF2A3A2A),
    onSecondaryContainer = Color(0xFFB8C5B8),

    tertiary = Color(0xFF808000), // Olive
    onTertiary = Color(0xFF0A1A0A),
    tertiaryContainer = Color(0xFF1A2A10),
    onTertiaryContainer = Color(0xFFB8C5B8),

    background = Color(0xFF0F190F),
    onBackground = Color(0xFFCCFFCC),

    surface = Color(0xFF1A2A1A),
    onSurface = Color(0xFFCCFFCC),
    surfaceVariant = Color(0xFF1A3A1A),
    onSurfaceVariant = Color(0xFFB8C5B8),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0A1A0A),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFF3CB371),
    outlineVariant = Color(0xFF2A3A2A),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFF3CB371),
)
val SakuraSunsetColorScheme = darkColorScheme(
    primary = Color(0xFFE91E63), // Deep Pink/Raspberry
    onPrimary = Color(0xFF0A0A0A),
    primaryContainer = Color(0xFF331520),
    onPrimaryContainer = Color(0xFFFFC0CB), // Pale Pink

    secondary = Color(0xFFBA68C8), // Lavender Accent
    onSecondary = Color(0xFF0A0A0A),
    secondaryContainer = Color(0xFF2A1A3A),
    onSecondaryContainer = Color(0xFFDCC5E0),

    tertiary = Color(0xFFF48FB1), // Light Pink Accent
    onTertiary = Color(0xFF0A0A0A),
    tertiaryContainer = Color(0xFF2A1A2A),
    onTertiaryContainer = Color(0xFFDCC5E0),

    background = Color(0xFF0F0F14), // Dark background
    onBackground = Color(0xFFF0F0F5),

    surface = Color(0xFF1A1A2A), // Dark surface
    onSurface = Color(0xFFF0F0F5),
    surfaceVariant = Color(0xFF331520),
    onSurfaceVariant = Color(0xFFDCC5E0),

    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0A0A0A),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = Color(0xFFE91E63),
    outlineVariant = Color(0xFF2A1A3A),
    scrim = Color(0xFF000000),

    surfaceTint = Color(0xFFE91E63),
)

val colorSchemes = listOf(
    ColorSchemeDTO("fire" ,FireColorScheme),
    ColorSchemeDTO("ice" ,IceColorScheme),
    ColorSchemeDTO("venom" ,VenomColorScheme),
    ColorSchemeDTO("royal" ,RoyalColorScheme),
    ColorSchemeDTO("shadow" ,ShadowColorScheme),
    ColorSchemeDTO("steel" ,SteelColorScheme),
    ColorSchemeDTO("crimson" ,CrimsonDawnColorScheme),
    ColorSchemeDTO("deepOcean" ,DeepOceanColorScheme),
    ColorSchemeDTO("desert" ,DesertDuneColorScheme),
    ColorSchemeDTO("neonSynth" ,NeonSynthColorScheme),
    ColorSchemeDTO("EmeraldForest" ,EmeraldForestColorScheme),
    ColorSchemeDTO("princessPink", SakuraSunsetColorScheme)
)