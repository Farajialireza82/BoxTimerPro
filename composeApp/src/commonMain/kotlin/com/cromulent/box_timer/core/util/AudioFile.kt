package com.cromulent.box_timer.core.util

import boxtimerpro.composeapp.generated.resources.Res
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import boxtimerpro.composeapp.generated.resources.audio_title_air_horn_long
import boxtimerpro.composeapp.generated.resources.audio_title_air_horn_short
import boxtimerpro.composeapp.generated.resources.audio_title_barcode
import boxtimerpro.composeapp.generated.resources.audio_title_beep
import boxtimerpro.composeapp.generated.resources.audio_title_bell_single
import boxtimerpro.composeapp.generated.resources.audio_title_bell_three_times
import boxtimerpro.composeapp.generated.resources.audio_title_buzzer_long
import boxtimerpro.composeapp.generated.resources.audio_title_buzzer_short
import boxtimerpro.composeapp.generated.resources.audio_title_clock_beep
import boxtimerpro.composeapp.generated.resources.audio_title_drop
import boxtimerpro.composeapp.generated.resources.audio_title_electric_beep
import boxtimerpro.composeapp.generated.resources.audio_title_fight_deep_voice
import boxtimerpro.composeapp.generated.resources.audio_title_gun_shot
import boxtimerpro.composeapp.generated.resources.audio_title_modern_drop
import boxtimerpro.composeapp.generated.resources.audio_title_mute
import boxtimerpro.composeapp.generated.resources.audio_title_pistol_shot
import boxtimerpro.composeapp.generated.resources.audio_title_whistle

@Serializable
data class AudioFile(
    val titleResKey: String, // String key for serialization
    val uri: String?
) {
    // Helper function to get the StringResource
    fun getTitleRes(): StringResource {
        return when (titleResKey) {
            "audio_title_mute" -> Res.string.audio_title_mute
            "audio_title_beep" -> Res.string.audio_title_beep
            "audio_title_bell_single" -> Res.string.audio_title_bell_single
            "audio_title_bell_three_times" -> Res.string.audio_title_bell_three_times
            "audio_title_whistle" -> Res.string.audio_title_whistle
            "audio_title_fight_deep_voice" -> Res.string.audio_title_fight_deep_voice
            "audio_title_air_horn_long" -> Res.string.audio_title_air_horn_long
            "audio_title_air_horn_short" -> Res.string.audio_title_air_horn_short
            "audio_title_buzzer_long" -> Res.string.audio_title_buzzer_long
            "audio_title_buzzer_short" -> Res.string.audio_title_buzzer_short
            "audio_title_gun_shot" -> Res.string.audio_title_gun_shot
            "audio_title_pistol_shot" -> Res.string.audio_title_pistol_shot
            "audio_title_barcode" -> Res.string.audio_title_barcode
            "audio_title_clock_beep" -> Res.string.audio_title_clock_beep
            "audio_title_electric_beep" -> Res.string.audio_title_electric_beep
            "audio_title_drop" -> Res.string.audio_title_drop
            "audio_title_modern_drop" -> Res.string.audio_title_modern_drop
            else -> Res.string.audio_title_mute // Fallback
        }
    }
}
