package com.cromulent.box_timer.presentation.configuration_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cromulent.box_timer.presentation.theme.SubtitleColor
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import com.cromulent.box_timer.presentation.theme.IceColorScheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AngleLeft
import compose.icons.fontawesomeicons.solid.AngleRight
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Backspace
import compose.icons.fontawesomeicons.solid.Backward
import compose.icons.fontawesomeicons.solid.CaretLeft
import compose.icons.fontawesomeicons.solid.FastBackward
import compose.icons.fontawesomeicons.solid.FastForward
import compose.icons.fontawesomeicons.solid.Forward
import compose.icons.fontawesomeicons.solid.HandPointLeft
import compose.icons.fontawesomeicons.solid.Minus
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.QuoteLeft
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimerSetter(
    title: String,
    timeInMillis: Long,
    timeUnitLabel: String,
    onPlusClicked: () -> Unit,
    onDoublePlusClicked: () -> Unit,
    onDoubleMinusClicked: () -> Unit,
    onMinusClicked: () -> Unit,
    isPlusButtonEnabled: Boolean,
    isMinusButtonEnabled: Boolean,
    isDoublePlusEnabled: Boolean,
    isDoubleMinusEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.2.dp, MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.3f)),
        colors = CardDefaults.cardColors(
            containerColor = Transparent,
        ),
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TimerControlButton(
                    icon = FontAwesomeIcons.Solid.AngleLeft,
                    onClick = onDoubleMinusClicked,
                    enabled = isDoubleMinusEnabled
                )
                Spacer(modifier = Modifier.width(12.dp))

                // Minus button
                TimerControlButton(
                    icon = FontAwesomeIcons.Solid.Minus,
                    onClick = onMinusClicked,
                    enabled = isMinusButtonEnabled
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = timeUnitLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(20.dp))

                // Plus button
                TimerControlButton(
                    icon = FontAwesomeIcons.Solid.Plus,
                    onClick = onPlusClicked,
                    enabled = isPlusButtonEnabled
                )

                Spacer(modifier = Modifier.width(12.dp))

                TimerControlButton(
                    icon = FontAwesomeIcons.Solid.AngleRight,
                    onClick = onDoublePlusClicked,
                    enabled = isDoublePlusEnabled
                )

            }
        }
    }
}

@Preview
@Composable
private fun TimeSetterPrev() {

    MaterialTheme(colorScheme = IceColorScheme) {

        TimerSetter(
            title = "Round Settings",
            timeInMillis = 300000,
            isMinusButtonEnabled = true,
            isPlusButtonEnabled = true,
            onPlusClicked = {},
            onMinusClicked = {},
            timeUnitLabel = "10 seconds",
            onDoublePlusClicked = {},
            onDoubleMinusClicked = {},
            isDoublePlusEnabled = true,
            isDoubleMinusEnabled = true
        )

    }

}