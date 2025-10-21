package com.cromulent.box_timer.core.service

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.*
import com.cromulent.box_timer.R

fun Builder.addTimerActions(
    context: Context,
    isPaused: Boolean,
): Builder = this
    .clearActions()
    .addAction(
        if(isPaused) R.drawable.ic_play else R.drawable.ic_pause,
        if(isPaused) "Play" else "Pause",
        PendingIntent.getService(
            context,
            0,
            Intent(context, TimerService::class.java).also {
                it.action = TimerService.Actions.TOGGLE.toString()
            },
            FLAG_IMMUTABLE
        )
    )
    .addAction(
        R.drawable.ic_play,
        "Exit",
        PendingIntent.getService(
            context,
            0,
            Intent(context, TimerService::class.java).also {
                it.action = TimerService.Actions.RESET.toString()
            },
            FLAG_IMMUTABLE
        )
    )