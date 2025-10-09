package com.cromulent.box_timer.presentation.configuration_screen.util

import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.workout_mode_boxing
import boxtimerpro.composeapp.generated.resources.workout_mode_boxing_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_boxing_emoji
import boxtimerpro.composeapp.generated.resources.workout_mode_cardio
import boxtimerpro.composeapp.generated.resources.workout_mode_cardio_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_cardio_emoji
import boxtimerpro.composeapp.generated.resources.workout_mode_custom
import boxtimerpro.composeapp.generated.resources.workout_mode_custom_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_custom_emoji
import boxtimerpro.composeapp.generated.resources.workout_mode_hiit
import boxtimerpro.composeapp.generated.resources.workout_mode_hiit_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_hiit_emoji
import boxtimerpro.composeapp.generated.resources.workout_mode_mma
import boxtimerpro.composeapp.generated.resources.workout_mode_mma_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_mma_emoji
import boxtimerpro.composeapp.generated.resources.workout_mode_strength
import boxtimerpro.composeapp.generated.resources.workout_mode_strength_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_strength_emoji
import boxtimerpro.composeapp.generated.resources.workout_mode_tabata
import boxtimerpro.composeapp.generated.resources.workout_mode_tabata_desc
import boxtimerpro.composeapp.generated.resources.workout_mode_tabata_emoji
import org.jetbrains.compose.resources.StringResource

enum class WorkoutMode(
    val displayNameRes: StringResource,
    val iconRes: StringResource,
    val descriptionRes: StringResource,
    val roundDuration: Long, // in seconds
    val restDuration: Long,  // in seconds
    val rounds: Int
) {
    MMA(
        Res.string.workout_mode_mma,
        Res.string.workout_mode_mma_emoji,
        Res.string.workout_mode_mma_desc,
        300000,
        60000,
        3
    ),
    BOXING(
        Res.string.workout_mode_boxing,
        Res.string.workout_mode_boxing_emoji,
        Res.string.workout_mode_boxing_desc,
        180000,
        60000,
        12
    ),
    STRENGTH(
        Res.string.workout_mode_strength,
        Res.string.workout_mode_strength_emoji,
        Res.string.workout_mode_strength_desc,
        120000,
        90000,
        6
    ),
    CARDIO(
        Res.string.workout_mode_cardio,
        Res.string.workout_mode_cardio_emoji,
        Res.string.workout_mode_cardio_desc,
        60000,
        30000,
        10
    ),
    HIIT(
        Res.string.workout_mode_hiit,
        Res.string.workout_mode_hiit_emoji,
        Res.string.workout_mode_hiit_desc,
        45000,
        15000,
        8
    ),
    TABATA(
        Res.string.workout_mode_tabata,
        Res.string.workout_mode_tabata_emoji,
        Res.string.workout_mode_tabata_desc,
        20000,
        10000,
        8
    ),
    CUSTOM(
        Res.string.workout_mode_custom,
        Res.string.workout_mode_custom_emoji,
        Res.string.workout_mode_custom_desc,
        60000,
        30000,
        5
    );

}