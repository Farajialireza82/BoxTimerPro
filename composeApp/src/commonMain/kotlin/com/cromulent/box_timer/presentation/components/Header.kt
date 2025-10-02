package com.cromulent.box_timer.presentation.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.back_ic
import boxtimerpro.composeapp.generated.resources.settings_ic
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Header(
    title: String,
    subtitle: String,
    hasBackButton: Boolean = false,
    hasActionButton: Boolean = false,
    actionButtonResource: DrawableResource? = null,
    onBackButtonClicked: () -> Unit = {},
    onActionButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {


    val titleGradientBrush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        start = Offset(0f, 0f), // Top-left corner
        end = Offset(1000f, 1000f), // Bottom-right corner, approximates 135deg
        tileMode = TileMode.Clamp
    )

    Box(
        modifier = modifier
            .padding(vertical = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                fontSize = 38.sp,
                fontWeight = FontWeight.W800,
                style = TextStyle(
                    brush = titleGradientBrush
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )



            Text(
                text = subtitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

        }


        if(hasBackButton) {
            IconButton(
                onClick = onBackButtonClicked,
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    painter = painterResource(Res.drawable.back_ic),
                    contentDescription = null
                )
            }
        }


        if(hasActionButton) {
            IconButton(
                onClick = onActionButtonClicked,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    painter = painterResource(actionButtonResource!!),
                    contentDescription = null
                )
            }
        }
    }

}

@Preview
@Composable
private fun HeaderPrev() {

    Header(
        title = "Settings",
        subtitle = "Customize Your Experience",
        hasBackButton = true,
        hasActionButton = true,
        actionButtonResource = Res.drawable.settings_ic
    )

}
