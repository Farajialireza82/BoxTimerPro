package com.cromulent.box_timer.data

import android.app.PendingIntent
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.cromulent.box_timer.data.repository.TimerRepositoryImpl
import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.presentation.timer_screen.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface AppContainer {
    val timerState: MutableStateFlow<TimerState>
}


class DefaultAppContainer() : AppContainer {

    override val timerState: MutableStateFlow<TimerState> by lazy {
        MutableStateFlow(
            TimerState()
        )
    }

}