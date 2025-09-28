package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist
import com.cromulent.box_timer.core.util.formatTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerCircleIndicator(
    currentTimeMillis: Long,
    totalTimeMillis: Long,
    isRunning: Boolean = false,
    message: String,
    modifier: Modifier = Modifier,
) {

    val progress = currentTimeMillis / totalTimeMillis.toFloat()

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {


        CircularProgressIndicator(
            modifier = Modifier
                .size(400.dp),
            progress = { progress },
            trackColor = CoralMist,
            strokeWidth = 10.dp,
            color = CoralHaze
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier
                    .animateContentSize()
                    .padding(vertical = 4.dp),
                text = formatTime(totalTimeMillis - currentTimeMillis),
                fontSize = 64.sp,
                fontWeight = FontWeight.W900,
                color = Color.White
            )


            Text(
                modifier = Modifier
                    .animateContentSize()
                    .padding(vertical = 4.dp),
                text = message,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center,
                color = if (isRunning) CoralHaze else Color.LightGray
            )

        }
    }

}

@Preview
@Composable
private fun Prev() {

    TimerCircleIndicator(
        currentTimeMillis = 1000L,
        totalTimeMillis = 100000L,
        isRunning = false,
        "Ready"
    )

}