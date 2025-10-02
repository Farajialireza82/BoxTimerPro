package com.cromulent.box_timer.presentation.configuration_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cromulent.box_timer.presentation.theme.SubtitleColor
import com.cromulent.box_timer.core.util.formatTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerSetter(
    title: String,
    timeInMillis: Long,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer),
        colors = CardDefaults.cardColors(
            containerColor = White.copy(alpha = 0.05f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header with title and time display
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = White
                )

                Text(
                    text = formatTime(timeInMillis),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Controls section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Minus button
                TimerControlButton(
                    icon = "−", // Using proper minus symbol
                    onClick = onMinusClicked,
                    enabled = timeInMillis > 15000 // Minimum 15 seconds
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Time unit label
                Text(
                    text = "30 seconds",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Plus button
                TimerControlButton(
                    icon = "+",
                    onClick = onPlusClicked,
                    enabled = timeInMillis < 3600000 // Maximum 60 minutes
                )
            }
        }
    }
}

@Composable
fun AdvancedTimerSetter(
    title: String,
    timeInMillis: Long,
    onTimeChanged: (Long) -> Unit,
    modifier: Modifier = Modifier,
    incrementSeconds: Int = 15, // Default 15-second increments
    minTimeMillis: Long = 15000, // 15 seconds minimum
    maxTimeMillis: Long = 3600000 // 60 minutes maximum
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer),
        colors = CardDefaults.cardColors(
            containerColor = White.copy(alpha = 0.05f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = White
                )

                Text(
                    text = formatTime(timeInMillis),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Quick preset buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val presets = when {
                    title.contains("Round", ignoreCase = true) ->
                        listOf(60000L, 120000L, 180000L, 300000L) // 1, 2, 3, 5 minutes
                    else ->
                        listOf(30000L, 60000L, 90000L, 120000L) // 30sec, 1, 1.5, 2 minutes
                }

                presets.forEach { preset ->
                    TimerPresetButton(
                        time = preset,
                        isSelected = timeInMillis == preset,
                        onClick = { onTimeChanged(preset) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Fine control buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimerControlButton(
                    icon = "−",
                    onClick = {
                        val newTime = (timeInMillis - (incrementSeconds * 1000))
                            .coerceAtLeast(minTimeMillis)
                        onTimeChanged(newTime)
                    },
                    enabled = timeInMillis > minTimeMillis
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "${incrementSeconds}s increments",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(20.dp))

                TimerControlButton(
                    icon = "+",
                    onClick = {
                        val newTime = (timeInMillis + (incrementSeconds * 1000))
                            .coerceAtMost(maxTimeMillis)
                        onTimeChanged(newTime)
                    },
                    enabled = timeInMillis < maxTimeMillis
                )
            }
        }
    }
}

@Composable
private fun TimerPresetButton(
    time: Long,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .padding(horizontal = 2.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSecondaryContainer
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent
        )
    ) {
        Text(
            text = formatTime(time),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun TimerControlButton(
    icon: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(40.dp)
            .clickable(enabled = enabled) { onClick() },
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (enabled) MaterialTheme.colorScheme.secondary else SubtitleColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = icon,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = if (enabled) MaterialTheme.colorScheme.secondary else SubtitleColor
            )
        }
    }
}

@Preview
@Composable
private fun TimeSetterPrev() {

    AdvancedTimerSetter(
        title = "Round Settings",
        timeInMillis = 300000,
        onTimeChanged = {},
        incrementSeconds = 30,
        minTimeMillis = 30000,
        maxTimeMillis = 20000
    )
    
}

@Preview
@Composable
private fun Preview() {

    TimerSetter(
        title = "Round Settings",
        timeInMillis = 300000,
        onPlusClicked = {},
        onMinusClicked = {  },
    )
    
}