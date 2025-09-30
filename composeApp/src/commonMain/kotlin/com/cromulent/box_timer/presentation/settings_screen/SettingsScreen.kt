package com.cromulent.box_timer.presentation.settings_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist
import com.cromulent.box_timer.core.theme.SubtitleColor
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.settings_screen.SettingsActions.ToggleMuteAllSounds
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowRight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreenRoot(
    viewModel: SettingsViewModel,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state by viewModel.state.collectAsState()

    SettingsScreen(
        state = state,
        onAction = viewModel::onAction,
        onBackButtonClicked = onBackButtonClicked,
        modifier = modifier
    )

}


@Composable
private fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val appSettings = state.appSettings
    val scrollState = rememberScrollState()

    MaterialTheme {

        Scaffold(
            modifier = Modifier,
        ) {

            Column(
                modifier = modifier
                    .background(backgroundGradientBrush)
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Header(
                    modifier = Modifier,
                    title = "Settings",
                    subtitle = "Customize Your Experience",
                    hasBackButton = true,
                    onBackButtonClicked = onBackButtonClicked
                )

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    TitleText("Audio Settings")

                    Spacer(Modifier.size(20.dp))

                    SettingSwitchCard(
                        isChecked = appSettings.muteAllSounds,
                        title = "Mute All Sounds",
                        subtitle = "Mute all sounds in the app",
                        onCheckedChange = { onAction(ToggleMuteAllSounds(it)) }
                    )

                    Spacer(Modifier.size(10.dp))

                    SettingsStringPickerCard(
                        title = "Countdown Sound",
                        subtitle = "Sound before round starts",
                        selectedTitle = appSettings.countDownAudioFile.title,
                        items = state.countDownAudioFiles.map { it.title },
                    ) {
                        onAction(SettingsActions.SetCountdownSound(state.countDownAudioFiles[it]))
                    }

                    Spacer(Modifier.size(10.dp))

                    SettingsStringPickerCard(
                        title = "Start Round Sound",
                        subtitle = "Sound when round begins",
                        selectedTitle = appSettings.startRoundAudioFile.title,
                        items = state.startRoundAudioFiles.map { it.title },
                    ) {
                        onAction(SettingsActions.SetStartRoundSound(state.startRoundAudioFiles[it]))
                    }

                    Spacer(Modifier.size(10.dp))

                    SettingsStringPickerCard(
                        title = "End Round Sound",
                        subtitle = "Sound when round ends",
                        selectedTitle = appSettings.endRoundAudioFile.title,
                        items = state.endRoundAudioFiles.map { it.title },
                    ) {
                        onAction(SettingsActions.SetEndRoundSound(state.endRoundAudioFiles[it]))
                    }

                    Spacer(Modifier.size(10.dp))

                    SettingSwitchCard(
                        isChecked = appSettings.isVibrationEnabled,
                        title = "Vibration",
                        subtitle = "Haptic feedback for timer events",
                        onCheckedChange = {
                            onAction(SettingsActions.ToggleVibrationHaptic(it))
                        }
                    )

                    Spacer(Modifier.size(30.dp))

                    TitleText("Display Settings")

                    Spacer(Modifier.size(20.dp))

                    SettingSwitchCard(
                        isChecked = appSettings.keepScreenOnEnabled,
                        title = "Keep Screen On",
                        subtitle = "Prevent screen from sleeping during workout",
                        onCheckedChange = {
                            onAction(SettingsActions.ToggleKeepScreenOn(it))

                        }
                    )

                    Spacer(Modifier.size(10.dp))

                    SettingSwitchCard(
                        isChecked = appSettings.allowRotation,
                        title = "Allow Rotation",
                        subtitle = "Enable landscape mode",
                        onCheckedChange = {
                            onAction(SettingsActions.ToggleAllowRotation(it))
                        }
                    )





                    Spacer(Modifier.size(30.dp))

                    TitleText("About & Support")

                    Spacer(Modifier.size(20.dp))


                    SettingCard(
                        title = "App Version",
                        subtitle = "Current version information",
                        onClick = { }
                    ) {
                        Text(
                            text = "v1.2.0",
                            color = SubtitleColor,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                    }

                    Spacer(Modifier.size(10.dp))

                    SettingCard(
                        title = "Feedback & Support",
                        subtitle = "Get help or send feedback",
                        onClick = { }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Contact",
                                color = CoralHaze,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = FontAwesomeIcons.Solid.ArrowRight,
                                tint = CoralHaze,
                                contentDescription = null
                            )
                        }
                    }

                    Spacer(Modifier.size(32.dp))


                }


            }
        }
    }


}

@Composable
private fun SettingCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .clickable(onClick != null, onClick = { onClick?.invoke() })
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = White.copy(alpha = 0.05f)
        ),
        border = BorderStroke(1.dp, White.copy(alpha = 0.1f))
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = White,
                )
                Text(
                    text = subtitle,
                    textAlign = TextAlign.Center,
                    color = White.copy(alpha = 0.6f)
                )
            }

            Spacer(Modifier.size(2.dp))

            content()


        }
    }


}


@Composable
private fun SettingsStringPickerCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    selectedTitle: String,
    items: List<String>,
    onItemSelected: (index: Int) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }


    SettingCard(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        onClick = null
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .padding(2.dp)
                    .clickable { expanded = true }
                    .widthIn(min = 70.dp),
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
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFF6B35)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = CoralMist,
                modifier = Modifier
                    .background(color = CoralMist)
                    .widthIn(min = 150.dp)
                    .clip(RoundedCornerShape(24.dp)),
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = if (item == selectedTitle)
                                    Color(0xFFFF6B35)
                                else
                                    White,
                                fontWeight = if (item == selectedTitle)
                                    FontWeight.Bold
                                else
                                    FontWeight.Normal
                            )
                        },
                        onClick = {
                            onItemSelected(index)
                            expanded = false
                        }
                    )
                }
            }
        }

    }


}

@Composable
private fun SettingSwitchCard(
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
                checkedThumbColor = CoralMist,
                checkedTrackColor = CoralHaze,
                uncheckedTrackColor = White
            )
        )
    }


}

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier
) {

    Text(text.uppercase(), modifier = modifier, style = SettingTitleStyle)

}

private val SettingTitleStyle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.W800,
    color = CoralHaze,
    letterSpacing = 2.sp,
)