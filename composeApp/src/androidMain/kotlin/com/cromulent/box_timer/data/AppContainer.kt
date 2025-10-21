package com.cromulent.box_timer.data

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.cromulent.box_timer.presentation.timer_screen.TimerState
import kotlinx.coroutines.flow.MutableStateFlow

interface AppContainer {
    val timerState: MutableStateFlow<TimerState>
    val notificationManager: NotificationManagerCompat
}


class DefaultAppContainer(context: Context) : AppContainer {

    override val timerState: MutableStateFlow<TimerState> by lazy {
        MutableStateFlow(
            TimerState()
        )
    }

    override val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(context)
    }

}