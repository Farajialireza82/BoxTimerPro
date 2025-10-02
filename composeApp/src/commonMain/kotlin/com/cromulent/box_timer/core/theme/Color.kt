package com.cromulent.box_timer.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

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


val backgroundGradientBrush = Brush.linearGradient(
    colors = listOf(
        SootyGray,
        StormCloudGray,
        CoffeeBeanBrown
    ),
    start = Offset(0f, 0f), // Top-left corner
    end = Offset(1000f, 1000f), // Bottom-right corner, approximates 145deg
    tileMode = TileMode.Clamp
)

val titleGradientBrush = Brush.linearGradient(
    colors = listOf(
        CoralHaze,
        AmberGlow,
        GoldenSun
    ),
    start = Offset(0f, 0f), // Top-left corner
    end = Offset(1000f, 1000f), // Bottom-right corner, approximates 135deg
    tileMode = TileMode.Clamp
)

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

val ArcticNight = Color(0xFF0a0e1a)
val FrostedSteel = Color(0xFF1a1e2a)
val GlacierBlue = Color(0xFF102a3a)
val DeepFreeze = Color(0xFF0f1419)
val IcyDusk = Color(0xFF1a2d3d)
val CrystalAqua = Color(0xFF4ECDC4)
val FrostWhite = Color(0xFFE0F7FA)
val IceSubtitle = Color(0xFFB0BEC5)
val IceSecondary = Color(0xFF1e3a47)
val AuroraMist = Color(0xFF1a2d3d)
val CyanGlow = Color(0xFF44A8F0)
val ElectricBlue = Color(0xFF00B8D4)

val iceBackgroundGradient = Brush.linearGradient(
    colors = listOf(DeepFreeze, ArcticNight, IcyDusk),
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f),
    tileMode = TileMode.Clamp
)

val iceTitleGradient = Brush.linearGradient(
    colors = listOf(CyanGlow, CrystalAqua, FrostWhite),
    start = Offset(0f, 0f),
    end = Offset(1000f, 1000f),
    tileMode = TileMode.Clamp
)


// ICE THEME
val IceColorScheme = darkColorScheme(
    primary = CrystalAqua,
    onPrimary = ArcticNight,
    primaryContainer = IcyDusk,
    onPrimaryContainer = FrostWhite,

    secondary = CyanGlow,
    onSecondary = ArcticNight,
    secondaryContainer = IceSecondary,
    onSecondaryContainer = IceSubtitle,

    tertiary = ElectricBlue,
    onTertiary = ArcticNight,
    tertiaryContainer = GlacierBlue,
    onTertiaryContainer = FrostWhite,

    background = DeepFreeze,
    onBackground = FrostWhite,

    surface = FrostedSteel,
    onSurface = FrostWhite,
    surfaceVariant = AuroraMist,
    onSurfaceVariant = IceSecondary,

    error = Color(0xFFFF6B6B),
    onError = ArcticNight,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    outline = CrystalAqua,
    outlineVariant = IceSecondary,
    scrim = Color(0xFF000000),

    surfaceTint = CrystalAqua,
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