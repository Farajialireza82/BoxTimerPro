package com.cromulent.box_timer.domain

import com.cromulent.box_timer.core.util.AudioFile
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val muteAllSounds: Boolean = false,
    val countDownAudioFile: AudioFile = AudioFile("Beep", "files/beep.mp3"),
    val startRoundAudioFile: AudioFile = AudioFile("Bell Single", "files/bell-single.mp3"),
    val endRoundAudioFile: AudioFile = AudioFile("Bell Three Times", "files/bell-three-times.mp3"),
    val isVibrationEnabled: Boolean = true,
    val keepScreenOnEnabled: Boolean = true,
    val allowRotation: Boolean = true,
    val colorSchemeId: String = "fire",
)