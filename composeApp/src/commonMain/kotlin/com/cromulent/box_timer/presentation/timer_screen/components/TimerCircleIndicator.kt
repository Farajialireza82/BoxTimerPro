package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.core.util.formatTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerCircleIndicator(
    remainingTime: Long,
    progress: Float,
    isRunning: Boolean = false,
    message: String,
    modifier: Modifier = Modifier,
) {

    MyProgressIndicator(
        modifier = modifier
            .size(400.dp),
        progress = progress,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            BasicText(
                text = formatTime(remainingTime),
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 64.sp,
                ),
                style = TextStyle(
                    fontWeight = FontWeight.W900,
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 64.dp),
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
                color = if (isRunning) MaterialTheme.colorScheme.secondary else Color.LightGray
            )
        }

    }
}

@Preview
@Composable
private fun Prev() {
    TimerCircleIndicator(
        remainingTime = 3600,
        progress = 0.5f,
        isRunning = true,
        message = "FIGHT",
    )
}

@Composable
private fun MyProgressIndicator(
    progress: Float = 0.1f,
    trackColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    color: Color = MaterialTheme.colorScheme.secondary,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val coercedProgress = { progress.coerceIn(0f, 1f) }
    val stroke =
        with(LocalDensity.current) { Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round) }

    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        content()

        Canvas(
            Modifier
                .fillMaxSize()
        ) {
            drawCircularIndicator(
                270f,
                360f,
                trackColor,
                stroke
            )
            drawCircularIndicator(270f, coercedProgress() * 360f, color, stroke)
        }
    }


}

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke
) {
    // Use the smaller of width or height to ensure the circle fits within the canvas
    val maxDiameter = minOf(size.width, size.height)
    // Account for stroke width to avoid clipping
    val diameterOffset = stroke.width / 2
    val arcDimen = maxDiameter - 2 * diameterOffset
    // Calculate topLeft to center the arc
    val topLeftX = (size.width - arcDimen) / 2
    val topLeftY = (size.height - arcDimen) / 2
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(topLeftX, topLeftY),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}
