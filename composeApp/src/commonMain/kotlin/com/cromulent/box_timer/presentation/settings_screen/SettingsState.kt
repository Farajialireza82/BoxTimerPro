package com.cromulent.box_timer.presentation.settings_screen

import com.cromulent.box_timer.core.util.AudioFile
import com.cromulent.box_timer.domain.AppSettings

data class SettingsState(
    val appSettings: AppSettings = AppSettings(),
    val audioFiles: List<AudioFile> = listOf(
        AudioFile("Beep", "files/beep.mp3"),
        AudioFile("Single Bell", "files/bell-single.mp3"),
        AudioFile("Bell Three Times", "files/bell-three-times.mp3")
    ),
    val countDownAudioFiles: List<AudioFile> = listOf(
        AudioFile("Beep", "files/beep.mp3"),
        AudioFile("Barcode", "files/Barcode-Scanner.mp3"),
        AudioFile("Car Horn", "files/Car-Horn.mp3"),
        AudioFile("Clock Beep", "files/Clock-Beep.mp3"),
        AudioFile("Electric Beep", "files/Electric-Beep.mp3"),
        AudioFile("Drop", "files/Drop.mp3"),
        AudioFile("Modern Drop", "files/Modern-Drop.mp3"),
    ).sortedBy { it.title }
)
