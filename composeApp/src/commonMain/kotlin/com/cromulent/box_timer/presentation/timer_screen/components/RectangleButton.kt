package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.presentation.theme.SecondarySubtitleColor

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
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        )

    }


}