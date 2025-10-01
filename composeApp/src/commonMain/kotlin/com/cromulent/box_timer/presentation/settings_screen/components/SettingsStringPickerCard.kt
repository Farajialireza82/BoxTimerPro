package com.cromulent.box_timer.presentation.settings_screen.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.core.theme.CoralMist

@Composable
fun SettingsStringPickerCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    selectedTitle: String,
    onClick: () -> Unit = {},
) {


    SettingCard(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        onClick = onClick
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .padding(2.dp)
                    .width(100.dp),
                color = Color(0xFFFF6B35).copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFFF6B35)),

                ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    text = selectedTitle,
                    fontSize = 14.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFFFF6B35)
                )
            }

        }

    }


}