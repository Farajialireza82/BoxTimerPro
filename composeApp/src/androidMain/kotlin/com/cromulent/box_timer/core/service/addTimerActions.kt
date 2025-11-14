package com.cromulent.box_timer.core.service

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.*
import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.skip_next_24px
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
        "Reset",
        PendingIntent.getService(
            context,
            0,
            Intent(context, TimerService::class.java).also {
                it.action = TimerService.Actions.RESET.toString()
            },
            FLAG_IMMUTABLE
        )
    )
    .addAction(
        R.drawable.skip_next_24px,
        "Skip",
        PendingIntent.getService(
            context,
            0,
            Intent(context, TimerService::class.java).also {
                it.action = TimerService.Actions.SKIP.toString()
            },
            FLAG_IMMUTABLE
        )
    )

fun Builder.addRestAction(
    context: Context,
): Builder = this
    .clearActions()
    .addAction(
        R.drawable.ic_pause,
        "Reset",
        PendingIntent.getService(
            context,
            0,
            Intent(context, TimerService::class.java).also {
                it.action = TimerService.Actions.RESET.toString()
            },
            FLAG_IMMUTABLE
        )
    )