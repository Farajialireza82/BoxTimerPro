package com.cromulent.box_timer.presentation.settings_screen.components
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.battery_optimization_settings_subtitle
import boxtimerpro.composeapp.generated.resources.battery_optimization_settings_title
import boxtimerpro.composeapp.generated.resources.general_settings_title
import boxtimerpro.composeapp.generated.resources.ic_back
import boxtimerpro.composeapp.generated.resources.ic_forward
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Image
import compose.icons.fontawesomeicons.solid.ArrowRight
import compose.icons.fontawesomeicons.solid.Exclamation
import compose.icons.fontawesomeicons.solid.ExclamationTriangle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BatteryOptimizationWarningCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val warningColor = Color(0xFFFF9800) // Orange warning color
    
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = warningColor.copy(alpha = 0.1f)
        ),
        border = BorderStroke(1.5.dp, warningColor.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Warning Icon
            Icon(
                imageVector = FontAwesomeIcons.Solid.ExclamationTriangle,
                contentDescription = "Warning",
                tint = warningColor,
                modifier = Modifier.size(28.dp)
            )
            
            Spacer(Modifier.width(12.dp))
            
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(Res.string.battery_optimization_settings_title),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = warningColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(Res.string.battery_optimization_settings_subtitle),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }
            
            Spacer(Modifier.width(8.dp))
            
            // Arrow or action indicator
            Icon(
                painter = painterResource(Res.drawable.ic_forward),
                contentDescription = "Go to settings",
                tint = warningColor.copy(alpha = 0.7f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
