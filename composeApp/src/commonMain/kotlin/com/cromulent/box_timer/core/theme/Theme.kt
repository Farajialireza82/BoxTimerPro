package com.cromulent.box_timer.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BoxTimerProTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FireColorScheme,
        content = content
    )
}