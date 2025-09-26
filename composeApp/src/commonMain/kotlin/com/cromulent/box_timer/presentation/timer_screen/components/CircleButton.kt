package com.cromulent.box_timer.presentation.timer_screen.components
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist
import com.cromulent.box_timer.core.theme.SecondarySubtitleColor

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    onButtonClicked: () -> Unit,
    icon: ImageVector
) {

    IconButton(
        onClick = onButtonClicked,
        modifier = modifier
            .animateContentSize()
            .size(70.dp)
            .background(
                brush =
                    if (isActive) Brush.linearGradient(listOf(CoralHaze, CoralMist))
                    else Brush.linearGradient(
                        listOf(
                            SecondarySubtitleColor,
                            SecondarySubtitleColor
                        )
                    ),
                shape = CircleShape
            ),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .animateContentSize()
                .size(24.dp)
        )

    }


}