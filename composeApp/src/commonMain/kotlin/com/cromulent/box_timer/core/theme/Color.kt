package com.cromulent.box_timer.core.theme

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