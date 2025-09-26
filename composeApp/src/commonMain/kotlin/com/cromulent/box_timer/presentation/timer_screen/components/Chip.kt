package com.cromulent.box_timer.presentation.timer_screen.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist

@Composable
fun Chip(
    text: String
) {

    Row(
        modifier = Modifier
            .background(
                color = CoralMist,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = CoralHaze,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .widthIn(min = 60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = CoralHaze
        )
    }


}