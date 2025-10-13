package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.presentation.timer_screen.TimerScreenPortrait
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun calculateOptimalTextSize(
    textMeasurer: TextMeasurer,
    text: String,
    maxWidth: Int,
    maxHeight: Int,
    style: TextStyle
): TextUnit {
    var fontSize = 1f
    val maxFontSize = 24f
    
    while (fontSize <= maxFontSize) {
        val testStyle = style.copy(fontSize = fontSize.sp)
        val textLayoutResult = textMeasurer.measure(
            text = text,
            style = testStyle,
            constraints = Constraints(maxWidth = maxWidth, maxHeight = maxHeight)
        )
        
        if (textLayoutResult.size.width > maxWidth || textLayoutResult.size.height > maxHeight) {
            break
        }
        fontSize += 1f
    }
    
    return (fontSize - 1f).coerceAtLeast(5f).sp
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit? = null
) {

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = text,
            autoSize = if (textSize != null) null else TextAutoSize.StepBased(),
            maxLines = 1,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W800,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = textSize ?: LocalTextStyle.current.fontSize
            )
        )
    }


}

@Preview(widthDp = 350, heightDp = 720)
@Composable
private fun Orev() {
    TimerScreenPortrait(
        onAction = {},
        onBackButtonClicked = {}
    )
}