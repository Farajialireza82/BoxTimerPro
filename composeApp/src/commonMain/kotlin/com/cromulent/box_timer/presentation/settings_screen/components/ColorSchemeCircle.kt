package com.cromulent.box_timer.presentation.settings_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.theme_description
import boxtimerpro.composeapp.generated.resources.theme_title
import com.cromulent.box_timer.data.ColorSchemeDTO
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ColorSchemeCircle(
    colorSchemeDTO: ColorSchemeDTO,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    size: androidx.compose.ui.unit.Dp = 100.dp,
    onClick: () -> Unit
) {

    val colorScheme = colorSchemeDTO.darkColorScheme

    val colors = listOf(
        colorScheme.primary,
        colorScheme.secondary,
        colorScheme.surface,
        colorScheme.tertiary,
    )

    val sweepAngle = 90f

    val startAngles = listOf(-90f, 0f, 90f, 180f)

    Box(
        modifier = modifier
            .size(size)
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size)) {

            val canvasSize = size.toPx()
            val radius = canvasSize / 2f
            val arcSize = Size(canvasSize, canvasSize)

            colors.forEachIndexed { index, color ->
                drawArc(
                    color = color,
                    startAngle = startAngles[index],
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(0f, 0f),
                    size = arcSize
                )
            }


            drawCircle(
                color = if (isSelected) colorScheme.secondary else Transparent,
                radius = radius,
                style = Stroke(width = 8.dp.toPx()) // Draw only the border
            )



            drawCircle(
                color = if (isSelected) colorScheme.surfaceVariant else Transparent,
                radius = radius,
                style = Stroke(width = 3.dp.toPx()) // Draw only the border
            )
        }
    }
}

@Preview
@Composable
fun ColorSchemePicker(
    items: List<ColorSchemeDTO>,
    selectedColorSchemeId: String,
    onItemSelected: (id: String) -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
    ) {

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = stringResource(Res.string.theme_title),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = stringResource(Res.string.theme_description),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }



            LazyRow(
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {

                items(items) {

                    Spacer(Modifier.size(16.dp))

                    ColorSchemeCircle(
                        colorSchemeDTO = it,
                        size = 53.dp,
                        isSelected = it.id == selectedColorSchemeId,
                        onClick = { onItemSelected(it.id) }
                    )

                    if(it.id == items.last().id) Spacer(Modifier.size(16.dp))

                }
            }

        }
    }

}