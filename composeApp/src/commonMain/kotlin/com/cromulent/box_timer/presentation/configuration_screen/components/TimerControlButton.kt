package com.cromulent.box_timer.presentation.configuration_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TimerControlButton(
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {


    val color = if (enabled){
        MaterialTheme.colorScheme.secondary
    } else MaterialTheme.colorScheme.onSurface.copy(0.7f)

    IconButton(
        modifier = modifier
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    color = color
                ),
                shape = CircleShape
            )
            .size(40.dp),
        enabled = enabled,
        onClick = onClick,

        ) {
        Icon(
            modifier = Modifier.size(18.dp),
            tint = color,
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun TimerControlButton(
    icon: Painter,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {


    val color = if (enabled){
        MaterialTheme.colorScheme.secondary
    } else MaterialTheme.colorScheme.onSurface.copy(0.7f)

    IconButton(
        modifier = modifier
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    color = color
                ),
                shape = CircleShape
            )
            .size(40.dp),
        enabled = enabled,
        onClick = onClick,

        ) {
        Icon(
            modifier = Modifier.size(18.dp),
            tint = color,
            painter = icon,
            contentDescription = null
        )
    }
}