package com.cromulent.box_timer.core.util

fun formatTime(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return when {
        hours > 0 -> "$hours:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
        minutes > 0 -> "${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
        else -> "$seconds'"
    }
}