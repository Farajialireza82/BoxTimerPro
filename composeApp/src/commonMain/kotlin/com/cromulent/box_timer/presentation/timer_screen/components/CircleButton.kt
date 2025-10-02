package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.core.theme.AmberGlow
import com.cromulent.box_timer.core.theme.CoffeeBeanBrown
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist
import com.cromulent.box_timer.core.theme.NightSkyBlack
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
                    if (isActive) Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
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

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = SecondarySubtitleColor,
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
            .height(if (isActive) 84.dp else 72.dp)
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
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        )

    }


}