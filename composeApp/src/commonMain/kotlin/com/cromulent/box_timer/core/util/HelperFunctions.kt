package com.cromulent.box_timer.core.util

import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.presentation.configuration_screen.util.WorkoutMode

import kotlin.math.ceil

fun formatTime(milliseconds: Long): String {
    // 1. Convert milliseconds to seconds, rounding UP to the next whole second
    // We cast to Double for the division and ceiling, then back to Long.
    val roundedSeconds = ceil(milliseconds.toDouble() / 1000.0).toLong()

    // 2. Calculate the components based on the rounded total seconds
    val totalSeconds = roundedSeconds
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    // 3. Format the time string (This part is largely the same)
    return when {
        // If the total rounded time is 3600s or more
        hours > 0 -> "$hours:${minutes.toString().padStart(2, '0')}:${
            seconds.toString().padStart(2, '0')
        }"

        // If the total rounded time is 60s or more
        minutes > 0 -> "${minutes.toString().padStart(2, '0')}:${
            seconds.toString().padStart(2, '0')
        }"

        // If the total rounded time is less than 60s
        else -> "00:${seconds.toString().padStart(2, '0')}"
    }
}

fun findWorkoutMode(roundDuration: Long, restDuration: Long, totalRounds: Int) =
    WorkoutMode.entries
        .firstOrNull {
            it.rounds == totalRounds &&
                    it.roundDuration == roundDuration &&
                    it.restDuration == restDuration
        }
        ?: WorkoutMode.CUSTOM

fun TimerSettings.toWorkoutMode(): WorkoutMode {

    return WorkoutMode.entries
        .firstOrNull {
            it.rounds == totalRounds &&
                    it.roundDuration == roundDuration &&
                    it.restDuration == restDuration
        }
        ?: WorkoutMode.CUSTOM
}