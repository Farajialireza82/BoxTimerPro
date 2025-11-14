package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.presentation.theme.SecondarySubtitleColor
import org.jetbrains.compose.resources.imageResource

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = SecondarySubtitleColor,
    activeTextColor: Color = MaterialTheme.colorScheme.onSecondary,
    unactiveTextColor: Color = MaterialTheme.colorScheme.onSurface,
    text: String,
    onButtonClicked: () -> Unit,
) {

    Button(
        onClick = onButtonClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .animateContentSize()
            .height(72.dp)
            .background(
                color =
                    if (isActive) activeColor
                    else unactiveColor,
                shape = RoundedCornerShape(if (isActive) 12.dp else 24.dp)
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(if (isActive) 12.dp else 24.dp),
    ) {

        Text(
            text = text,
            color = if(isActive) activeTextColor else unactiveTextColor,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        )

    }


}

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = SecondarySubtitleColor,
    activeTextColor: Color = MaterialTheme.colorScheme.onSecondary,
    unactiveTextColor: Color = MaterialTheme.colorScheme.onSurface,
    icon: Painter,
    onButtonClicked: () -> Unit,
) {

    Button(
        onClick = onButtonClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .animateContentSize()
            .height(72.dp)
            .background(
                color =
                    if (isActive) activeColor
                    else unactiveColor,
                shape = RoundedCornerShape(if (isActive) 12.dp else 24.dp)
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(if (isActive) 12.dp else 24.dp),
    ) {

        Icon(
            painter = icon,
            contentDescription = null,
            tint = if(isActive) activeTextColor else unactiveTextColor,
            modifier = Modifier
                .size(36.dp)
        )

    }


}