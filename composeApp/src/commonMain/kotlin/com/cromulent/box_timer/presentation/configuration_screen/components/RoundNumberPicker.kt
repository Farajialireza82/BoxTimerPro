package com.cromulent.box_timer.presentation.configuration_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

@Composable
fun RoundNumberPicker(
    title: String = "Total Rounds",
    rounds: Int,
    onRoundsChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minRounds: Int = 1,
    maxRounds: Int = 50
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer),
        colors = CardDefaults.cardColors(
            containerColor = White.copy(alpha = 0.05f)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header with title and current value
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
                    text = rounds.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            
            Spacer(modifier = Modifier.height(15.dp))
            
            // Quick preset buttons for common round counts
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val commonRounds = listOf(3, 6, 10, 12, 15)
                
                commonRounds.forEach { preset ->
                    RoundPresetButton(
                        rounds = preset,
                        isSelected = rounds == preset,
                        onClick = { onRoundsChanged(preset) }
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
                        val newRounds = (rounds - 1).coerceAtLeast(minRounds)
                        onRoundsChanged(newRounds)
                    },
                    enabled = rounds > minRounds
                )
                
                Spacer(modifier = Modifier.width(20.dp))
                
                Text(
                    text = "rounds",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.width(20.dp))
                
                TimerControlButton(
                    icon = "+",
                    onClick = { 
                        val newRounds = (rounds + 1).coerceAtMost(maxRounds)
                        onRoundsChanged(newRounds)
                    },
                    enabled = rounds < maxRounds
                )
            }
        }
    }
}

@Composable
private fun RoundPresetButton(
    rounds: Int,
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
            text = rounds.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}