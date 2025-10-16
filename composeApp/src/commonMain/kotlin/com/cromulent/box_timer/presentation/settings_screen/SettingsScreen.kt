package com.cromulent.box_timer.presentation.settings_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.about_and_support_title
import boxtimerpro.composeapp.generated.resources.app_version_subtitle
import boxtimerpro.composeapp.generated.resources.app_version_title
import boxtimerpro.composeapp.generated.resources.audio_settings_title
import boxtimerpro.composeapp.generated.resources.contact_button_text
import boxtimerpro.composeapp.generated.resources.countdown_sound_subtitle
import boxtimerpro.composeapp.generated.resources.countdown_sound_title
import boxtimerpro.composeapp.generated.resources.display_settings_title
import boxtimerpro.composeapp.generated.resources.end_round_sound_subtitle
import boxtimerpro.composeapp.generated.resources.end_round_sound_title
import boxtimerpro.composeapp.generated.resources.feedback_support_subtitle
import boxtimerpro.composeapp.generated.resources.feedback_support_title
import boxtimerpro.composeapp.generated.resources.ic_dark
import boxtimerpro.composeapp.generated.resources.ic_light
import boxtimerpro.composeapp.generated.resources.keep_screen_on_subtitle
import boxtimerpro.composeapp.generated.resources.keep_screen_on_title
import boxtimerpro.composeapp.generated.resources.mute_all_sounds_subtitle
import boxtimerpro.composeapp.generated.resources.mute_all_sounds_title
import boxtimerpro.composeapp.generated.resources.settings_subtitle
import boxtimerpro.composeapp.generated.resources.settings_title
import boxtimerpro.composeapp.generated.resources.start_round_sound_subtitle
import boxtimerpro.composeapp.generated.resources.start_round_sound_title
import boxtimerpro.composeapp.generated.resources.vibration_subtitle
import boxtimerpro.composeapp.generated.resources.vibration_title
import com.cromulent.box_timer.BuildKonfig
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.presentation.theme.SubtitleColor
import com.cromulent.box_timer.presentation.theme.colorSchemes
import com.cromulent.box_timer.presentation.components.Header
import com.cromulent.box_timer.presentation.settings_screen.SettingsActions.ToggleMuteAllSounds
import com.cromulent.box_timer.presentation.settings_screen.components.AudioPickerBottomSheet
import com.cromulent.box_timer.presentation.settings_screen.components.ColorSchemePicker
import com.cromulent.box_timer.presentation.settings_screen.components.SettingCard
import com.cromulent.box_timer.presentation.settings_screen.components.SettingSwitchCard
import com.cromulent.box_timer.presentation.settings_screen.components.SettingsStringPickerCard
import com.cromulent.box_timer.presentation.settings_screen.components.TitleText
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowRight
import org.jetbrains.compose.resources.stringResource

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

    var countDownBsVisibility by remember { mutableStateOf(false) }
    var startRoundBsVisibility by remember { mutableStateOf(false) }
    var endRoundBsVisibility by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier,
        containerColor = Transparent
    ) {

        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Header(
                modifier = Modifier,
                title = stringResource(Res.string.settings_title),
                subtitle = stringResource(Res.string.settings_subtitle),
                hasBackButton = true,
                onBackButtonClicked = onBackButtonClicked,
                hasActionButton = true,
                actionButtonResource = if (appSettings.isDarkMode) Res.drawable.ic_dark else Res.drawable.ic_light,
                onActionButtonClicked = { onAction(SettingsActions.ToggleDarkMode) }
            )

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                TitleText(stringResource(Res.string.audio_settings_title))

                Spacer(Modifier.size(20.dp))

                SettingSwitchCard(
                    isChecked = appSettings.muteAllSounds,
                    title = stringResource(Res.string.mute_all_sounds_title),
                    subtitle = stringResource(Res.string.mute_all_sounds_subtitle),
                    onCheckedChange = { onAction(ToggleMuteAllSounds(it)) }
                )

                Spacer(Modifier.size(10.dp))

                SettingSwitchCard(
                    isChecked = appSettings.isVibrationEnabled,
                    title = stringResource(Res.string.vibration_title),
                    subtitle = stringResource(Res.string.vibration_subtitle),
                    onCheckedChange = {
                        onAction(SettingsActions.ToggleVibrationHaptic(it))
                    }
                )

                Spacer(Modifier.size(10.dp))

                SettingsStringPickerCard(
                    title = stringResource(Res.string.countdown_sound_title),
                    subtitle = stringResource(Res.string.countdown_sound_subtitle),
                    selectedTitle = appSettings.countDownAudioFile.title,
                    onClick = { countDownBsVisibility = true }
                )

                Spacer(Modifier.size(10.dp))

                SettingsStringPickerCard(
                    title = stringResource(Res.string.start_round_sound_title),
                    subtitle = stringResource(Res.string.start_round_sound_subtitle),
                    selectedTitle = appSettings.startRoundAudioFile.title,
                    onClick = { startRoundBsVisibility = true }
                )

                Spacer(Modifier.size(10.dp))

                SettingsStringPickerCard(
                    title = stringResource(Res.string.end_round_sound_title),
                    subtitle = stringResource(Res.string.end_round_sound_subtitle),
                    selectedTitle = appSettings.endRoundAudioFile.title,
                    onClick = { endRoundBsVisibility = true }
                )

                Spacer(Modifier.size(30.dp))

                TitleText(stringResource(Res.string.display_settings_title))

                Spacer(Modifier.size(20.dp))

                SettingSwitchCard(
                    isChecked = appSettings.keepScreenOnEnabled,
                    title = stringResource(Res.string.keep_screen_on_title),
                    subtitle = stringResource(Res.string.keep_screen_on_subtitle),
                    onCheckedChange = {
                        onAction(SettingsActions.ToggleKeepScreenOn(it))

                    }
                )

                Spacer(Modifier.size(20.dp))

                ColorSchemePicker(
                    items = colorSchemes,
                    selectedColorSchemeId = state.appSettings.colorSchemeId,
                    onItemSelected = { colorSchemeId ->
                        onAction(SettingsActions.SetColorScheme(colorSchemeId))
                    }
                )

                Spacer(Modifier.size(30.dp))

                TitleText(stringResource(Res.string.about_and_support_title))

                Spacer(Modifier.size(20.dp))


                SettingCard(
                    title = stringResource(Res.string.app_version_title),
                    subtitle = stringResource(Res.string.app_version_subtitle),
                    onClick = { }
                ) {
                    Text(
                        text = "v${BuildKonfig.APP_VERSION}",
                        color = SubtitleColor,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }

                Spacer(Modifier.size(10.dp))

                SettingCard(
                    title = stringResource(Res.string.feedback_support_title),
                    subtitle = stringResource(Res.string.feedback_support_subtitle),
                    onClick = { onAction(SettingsActions.OnFeedbackClick) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(Res.string.contact_button_text),
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


    // Bottom Sheets (reusing string resources)

    if (startRoundBsVisibility) {
        AudioPickerBottomSheet(
            onDismissRequest = { startRoundBsVisibility = false },
            onItemSelected = { onAction(SettingsActions.SetStartRoundSound(it)) },
            title = stringResource(Res.string.start_round_sound_title),
            selectedAudioFile = appSettings.startRoundAudioFile,
            playAudio = { onAction(SettingsActions.PlayAudio(it.uri)) },
            items = state.startRoundAudioFiles
        )
    }


    if (endRoundBsVisibility) {
        AudioPickerBottomSheet(
            onDismissRequest = { endRoundBsVisibility = false },
            onItemSelected = { onAction(SettingsActions.SetEndRoundSound(it)) },
            title = stringResource(Res.string.end_round_sound_title),
            selectedAudioFile = appSettings.endRoundAudioFile,
            playAudio = { onAction(SettingsActions.PlayAudio(it.uri)) },
            items = state.endRoundAudioFiles
        )
    }


    if (countDownBsVisibility) {
        AudioPickerBottomSheet(
            onDismissRequest = { countDownBsVisibility = false },
            onItemSelected = { onAction(SettingsActions.SetCountdownSound(it)) },
            title = stringResource(Res.string.countdown_sound_title),
            selectedAudioFile = appSettings.countDownAudioFile,
            playAudio = { onAction(SettingsActions.PlayAudio(it.uri)) },
            items = state.countDownAudioFiles
        )
    }
}