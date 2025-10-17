package com.cromulent.box_timer.presentation.configuration_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.presentation.configuration_screen.util.WorkoutMode
import org.jetbrains.compose.resources.stringResource

@Composable
fun ModeCard(
    mode: WorkoutMode,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    emojiSize: TextUnit = 32.sp,
    titleSize: TextUnit = 24.sp,
    descriptionSize: TextUnit = 12.sp,
    onClick: (WorkoutMode) -> Unit = {}
) {
    Card(
        modifier = modifier
            .aspectRatio(1.6f)
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(mode) },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 3.dp,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.05f
            )
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else White.copy(
                alpha = 0.05f
            ),
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(mode.iconRes),
                fontSize = emojiSize
            )

            Spacer(Modifier.size(4.dp))


            Text(
                text = stringResource(mode.displayNameRes),
                fontWeight = FontWeight.W600,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                fontSize = titleSize

            )

            AnimatedVisibility(isSelected) {
                Spacer(Modifier.size(4.dp))

                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp
                        )
                        .fillMaxWidth(),
                    text = stringResource(mode.descriptionRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = descriptionSize
                )
            }
        }
    }
}