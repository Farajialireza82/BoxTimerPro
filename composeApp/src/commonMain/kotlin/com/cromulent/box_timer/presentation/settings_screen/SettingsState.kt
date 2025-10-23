package com.cromulent.box_timer.presentation.settings_screen

import com.cromulent.box_timer.core.util.AudioFile
import com.cromulent.box_timer.domain.AppSettings

data class SettingsState(
    val appSettings: AppSettings = AppSettings(),
    val startRoundAudioFiles: List<AudioFile> = listOf(
        AudioFile("audio_title_mute", null),
        AudioFile("audio_title_bell_single",                    "files/bell-single.mp3"),
        AudioFile("audio_title_bell_three_times",                  "files/bell-three-times.mp3"),
        AudioFile("audio_title_whistle",                  "files/whistle.mp3"),
        AudioFile("audio_title_fight_deep_voice",                 "files/fight-deep-voice.mp3"),
        AudioFile("audio_title_air_horn_long",                  "files/air-horn-long.mp3"),
        AudioFile("audio_title_air_horn_short",                  "files/air-horn-short.mp3"),
        AudioFile("audio_title_buzzer_long",                  "files/buzzer-long.mp3"),
        AudioFile("audio_title_buzzer_short",                  "files/buzzer-short.mp3"),
        AudioFile("audio_title_gun_shot",                  "files/gun-shot.mp3"),
        AudioFile("audio_title_pistol_shot",                  "files/pistol-shot.mp3"),
    ),
    val endRoundAudioFiles: List<AudioFile> = listOf(
        AudioFile("audio_title_mute", null),
        AudioFile("audio_title_bell_single",                     "files/bell-single.mp3"),
        AudioFile("audio_title_bell_three_times",                     "files/bell-three-times.mp3"),
        AudioFile("audio_title_whistle",                     "files/whistle.mp3"),
        AudioFile("audio_title_air_horn_long",                     "files/air-horn-long.mp3"),
        AudioFile("audio_title_air_horn_short",                     "files/air-horn-short.mp3"),
        AudioFile("audio_title_buzzer_long",                     "files/buzzer-long.mp3"),
        AudioFile("audio_title_buzzer_short",                     "files/buzzer-short.mp3"),
        AudioFile("audio_title_gun_shot",                     "files/gun-shot.mp3"),
        AudioFile("audio_title_pistol_shot",                     "files/pistol-shot.mp3"),
    ),
    val countDownAudioFiles: List<AudioFile> = listOf(
        AudioFile("audio_title_mute", null),
        AudioFile("audio_title_beep",           "files/beep.mp3"),
        AudioFile("audio_title_barcode",                     "files/Barcode-Scanner.mp3"),
        AudioFile("audio_title_clock_beep",                     "files/Clock-Beep.mp3"),
        AudioFile("audio_title_electric_beep",                     "files/Electric-Beep.mp3"),
        AudioFile("audio_title_drop",                     "files/Drop.mp3"),
        AudioFile("audio_title_modern_drop",                     "files/Modern-Drop.mp3"),
    )
)
