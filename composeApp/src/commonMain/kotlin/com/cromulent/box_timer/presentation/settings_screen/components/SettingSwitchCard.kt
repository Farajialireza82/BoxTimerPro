package com.cromulent.box_timer.presentation.settings_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist

@Composable
fun SettingSwitchCard(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    title: String,
    subtitle: String,
    onCheckedChange: (Boolean) -> Unit
) {

    SettingCard(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        onClick = null
    ) {

        Switch(
            modifier = Modifier,
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.surfaceVariant,
                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = White,
                uncheckedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }


}