package com.cromulent.box_timer.presentation.timer_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.button_reset
import boxtimerpro.composeapp.generated.resources.ic_play
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = Color(0xFF353130),
    activeTextColor: Color = MaterialTheme.colorScheme.onSecondary,
    unactiveTextColor: Color = MaterialTheme.colorScheme.onSurface,
    text: String,
    onButtonClicked: () -> Unit,
) {

    RectangleButtonRoot(
        modifier,
        isActive,
        activeColor,
        unactiveColor,
        onButtonClicked,
    ){

        Text(
            text = text,
            color = if(isActive) activeTextColor else unactiveTextColor,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        )
    }
}

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = Color(0xFF353130),
    activeTextColor: Color = MaterialTheme.colorScheme.onSecondary,
    unactiveTextColor: Color = MaterialTheme.colorScheme.onSurface,
    icon: Painter,
    onButtonClicked: () -> Unit,
) {

    RectangleButtonRoot(
        modifier,
        isActive,
        activeColor,
        unactiveColor,
        onButtonClicked,
    ){

        Icon(
            painter = icon,
            contentDescription = null,
            tint = if(isActive) activeTextColor else unactiveTextColor,
            modifier = Modifier
                .size(36.dp)
        )

    }


}

@Composable
fun RectangleButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = Color(0xFF353130),
    activeTextColor: Color = MaterialTheme.colorScheme.onSecondary,
    unactiveTextColor: Color = MaterialTheme.colorScheme.onSurface,
    text: String,
    icon: Painter,
    onButtonClicked: () -> Unit,
) {

    RectangleButtonRoot(
        modifier,
        isActive,
        activeColor,
        unactiveColor,
        onButtonClicked,
    ){

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter = icon,
                contentDescription = null,
                tint = if(isActive) activeTextColor else unactiveTextColor,
                modifier = Modifier
                    .size(36.dp)
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = text,
                color = if(isActive) activeTextColor else unactiveTextColor,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp
            )
        }

    }

}

@Composable
private fun RectangleButtonRoot(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeColor: Color = MaterialTheme.colorScheme.secondary,
    unactiveColor: Color = Color(0xFF353130),
    onButtonClicked: () -> Unit,
    content: @Composable () -> Unit,
){
    Button(
        onClick = onButtonClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .animateContentSize()
            .height(72.dp)
            .background(
                color =
                    if (isActive) activeColor
                    else unactiveColor,
                shape = RoundedCornerShape(if (isActive) 12.dp else 24.dp)
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(if (isActive) 12.dp else 24.dp),
    ) {

       content()

    }
}

@Preview
@Composable
private fun Preview() {

    RectangleButton(
        modifier = Modifier
            .height(58.dp),
        isActive = false,
        unactiveTextColor = White,
        text = stringResource(Res.string.button_reset),
        icon = painterResource(Res.drawable.ic_play),
        onButtonClicked = {
        }
    )

}

@Preview
@Composable
private fun PreviewIcon() {

    RectangleButton(
        modifier = Modifier
            .height(58.dp),
        isActive = false,
        unactiveTextColor = White,
        icon = painterResource(Res.drawable.ic_play),
        onButtonClicked = {
        }
    )

}

@Preview
@Composable
private fun PreviewText() {

    RectangleButton(
        modifier = Modifier
            .height(58.dp),
        isActive = false,
        unactiveTextColor = White,
        text = stringResource(Res.string.button_reset),
        onButtonClicked = {
        }
    )

}