package com.cromulent.box_timer.domain

import com.cromulent.box_timer.core.util.AudioFile
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val muteAllSounds: Boolean = false,
    val countDownAudioFile: AudioFile = AudioFile("audio_title_beep", "files/beep.mp3"),
    val startRoundAudioFile: AudioFile = AudioFile("audio_title_bell_single", "files/bell-single.mp3"),
    val endRoundAudioFile: AudioFile = AudioFile("audio_title_bell_three_times", "files/bell-three-times.mp3"),
    val isVibrationEnabled: Boolean = true,
    val keepScreenOnEnabled: Boolean = true,
    val allowRotation: Boolean = true,
    val colorSchemeId: String = "ice",
    val isDarkMode: Boolean = true,
    val stopTimerOnClose: Boolean = true,
    val selectedLanguage: AppLanguage = AppLanguage.SYSTEM
)