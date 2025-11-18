package com.cromulent.box_timer.core.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.countdown_get_ready
import boxtimerpro.composeapp.generated.resources.title_counting_down
import boxtimerpro.composeapp.generated.resources.title_fight
import boxtimerpro.composeapp.generated.resources.title_paused
import boxtimerpro.composeapp.generated.resources.title_ready
import boxtimerpro.composeapp.generated.resources.title_rest
import boxtimerpro.composeapp.generated.resources.title_workout_complete
import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.presentation.configuration_screen.util.WorkoutMode
import org.jetbrains.compose.resources.stringResource

import kotlin.math.ceil
import kotlin.math.floor

fun formatTime(milliseconds: Long, roundUp: Boolean = true): String {
    // 1. Convert milliseconds to seconds, rounding UP to the next whole second
    // We cast to Double for the division and ceiling, then back to Long.
    val roundedSeconds = if(roundUp){
        ceil(milliseconds.toDouble() / 1000.0).toLong()
    } else floor(milliseconds.toDouble() / 1000.0).toLong()

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



fun formatTimeWithMillis(milliseconds: Long): String {

    val totalSeconds = milliseconds / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    val millis = (milliseconds % 1000) / 10

    return when {
        hours > 0 -> "$hours:${minutes.toString().padStart(2, '0')}:${
            seconds.toString().padStart(2, '0')
        }.${millis.toString().padStart(2, '0')}"

        minutes > 0 -> "${minutes.toString().padStart(2, '0')}:${
            seconds.toString().padStart(2, '0')
        }.${millis.toString().padStart(2, '0')}"

        else -> "00:${seconds.toString().padStart(2, '0')}.${millis.toString().padStart(2, '0')}"
    }
}

fun Long.timeFormat(roundUp: Boolean = true): String{
    return formatTime(this, roundUp)
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

fun NavController.popBackStackSafely() {
    if (previousBackStackEntry != null) {
        popBackStack()
    }
}

@Composable
fun translateCountdownText(countdownText: String): String {
    return if (countdownText.startsWith("Get Ready: ")) {
        val number = countdownText.substringAfter("Get Ready: ").toIntOrNull() ?: 0
        stringResource(Res.string.countdown_get_ready) + number
    } else {
        countdownText
    }
}

@Composable
fun getStringResource(key: String): String {
    return when (key) {
        "title_ready" -> stringResource(Res.string.title_ready)
        "title_fight" -> stringResource(Res.string.title_fight)
        "title_paused" -> stringResource(Res.string.title_paused)
        "title_rest" -> stringResource(Res.string.title_rest)
        "title_counting_down" -> stringResource(Res.string.title_counting_down)
        "title_workout_complete" -> stringResource(Res.string.title_workout_complete)
        else -> key
    }
}