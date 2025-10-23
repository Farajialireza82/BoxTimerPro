package com.cromulent.box_timer.presentation.settings_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier
) {

    Text(
        text.uppercase(),
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.W800,
            color = MaterialTheme.colorScheme.secondary,
            letterSpacing = 2.sp,
        )
    )
}