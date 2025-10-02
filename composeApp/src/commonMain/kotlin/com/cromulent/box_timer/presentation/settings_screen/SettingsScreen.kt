package com.cromulent.box_timer.presentation.settings_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.ic_play
import com.cromulent.box_timer.core.theme.CoralHaze
import com.cromulent.box_timer.core.theme.CoralMist
import com.cromulent.box_timer.core.theme.SubtitleColor
import com.cromulent.box_timer.core.theme.backgroundGradientBrush
import com.cromulent.box_timer.core.util.AudioFile
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.settings_screen.SettingsActions.ToggleMuteAllSounds
import com.cromulent.box_timer.presentation.settings_screen.components.AudioPickerBottomSheet
import com.cromulent.box_timer.presentation.settings_screen.components.SettingCard
import com.cromulent.box_timer.presentation.settings_screen.components.SettingSwitchCard
import com.cromulent.box_timer.presentation.settings_screen.components.SettingsStringPickerCard
import com.cromulent.box_timer.presentation.settings_screen.components.TitleText
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowRight
import org.jetbrains.compose.resources.painterResource
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

@Preview
@Composable
private fun SettingsScreen(
    state: SettingsState = SettingsState(),
    onAction: (SettingsActions) -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val appSettings = state.appSettings
    val scrollState = rememberScrollState()

    var countDownBsVisibility by remember { mutableStateOf(false) }
    var startRoundBsVisibility by remember { mutableStateOf(false) }
    var endRoundBsVisibility by remember { mutableStateOf(false) }


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
                        onClick = { countDownBsVisibility = true }
                    )

                    Spacer(Modifier.size(10.dp))

                    SettingsStringPickerCard(
                        title = "Start Round Sound",
                        subtitle = "Sound when round begins",
                        selectedTitle = appSettings.startRoundAudioFile.title,
                        onClick = { startRoundBsVisibility = true }
                    )

                    Spacer(Modifier.size(10.dp))

                    SettingsStringPickerCard(
                        title = "End Round Sound",
                        subtitle = "Sound when round ends",
                        selectedTitle = appSettings.endRoundAudioFile.title,
                        onClick = { endRoundBsVisibility = true }
                    )

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
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = FontAwesomeIcons.Solid.ArrowRight,
                                tint = MaterialTheme.colorScheme.secondary,
                                contentDescription = null
                            )
                        }
                    }

                    Spacer(Modifier.size(32.dp))

                }


            }
        }



        if (startRoundBsVisibility) {
            AudioPickerBottomSheet(
                onDismissRequest = { startRoundBsVisibility = false },
                onItemSelected = { onAction(SettingsActions.SetStartRoundSound(it)) },
                title = "Start Round Sound",
                selectedAudioFile = appSettings.startRoundAudioFile,
                playAudio = { onAction(SettingsActions.PlayAudio(it.uri)) },
                items = state.startRoundAudioFiles
            )
        }


        if (endRoundBsVisibility) {
            AudioPickerBottomSheet(
                onDismissRequest = { endRoundBsVisibility = false },
                onItemSelected = { onAction(SettingsActions.SetEndRoundSound(it)) },
                title = "End Round Sound",
                selectedAudioFile = appSettings.endRoundAudioFile,
                playAudio = { onAction(SettingsActions.PlayAudio(it.uri)) },
                items = state.endRoundAudioFiles
            )
        }


        if (countDownBsVisibility) {
            AudioPickerBottomSheet(
                onDismissRequest = { countDownBsVisibility = false },
                onItemSelected = { onAction(SettingsActions.SetCountdownSound(it)) },
                title = "Countdown Sound",
                selectedAudioFile = appSettings.countDownAudioFile,
                playAudio = { onAction(SettingsActions.PlayAudio(it.uri)) },
                items = state.countDownAudioFiles
            )
        }
    }
}