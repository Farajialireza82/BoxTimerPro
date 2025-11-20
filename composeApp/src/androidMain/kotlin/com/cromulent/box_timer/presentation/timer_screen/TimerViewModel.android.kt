package com.cromulent.box_timer.presentation.timer_screen

import android.app.Application
import android.app.Service.STOP_FOREGROUND_REMOVE
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.ServiceCompat.stopForeground
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cromulent.box_timer.core.service.TimerService
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.data.AppContainer
import com.cromulent.box_timer.data.DefaultAppContainer
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.timer.Lap
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

actual class TimerViewModel(
    settingsRepository: SettingsRepository,
    val audioPlayer: AudioPlayer,
    val systemEngine: SystemEngine,
    val context: Application
) : ViewModel() {

    private val appContainer = DefaultAppContainer(context)

    init {
        // Only start the timer if it's not already running
        // This prevents toggling when opening the app from notification
        if (!TimerService.isRunning) {
            Intent(context, TimerService::class.java).also {
                it.action = TimerService.Actions.TOGGLE.toString()
                context.startService(it)
            }
        }
    }


    actual fun onAction(action: TimerActions) {
        when (action) {
            is TimerActions.DeleteLap -> {
                Intent(context, TimerService::class.java).also {intent ->
                    intent.action = TimerService.Actions.DELETE_LAP.toString()
                    intent.putExtra("LAP_CREATE_TIME", action.lap.createTime)
                    intent.putExtra("LAP_ROUND_NUMBER", action.lap.roundNumber)
                    context.startService(intent)
                }
            }
            TimerActions.StartTimer -> {
                Intent(context, TimerService::class.java).also {
                    it.action = TimerService.Actions.TOGGLE.toString()
                    context.startService(it)
                }
            }
            TimerActions.PauseTimer -> {
                Intent(context, TimerService::class.java).also {
                    it.action = TimerService.Actions.TOGGLE.toString()
                    context.startService(it)
                }
            }
            TimerActions.ResetTimer -> {
                Intent(context, TimerService::class.java).also {
                    it.action = TimerService.Actions.RESET.toString()
                    context.startService(it)
                }
            }
            TimerActions.SkipTimer -> {
                Intent(context, TimerService::class.java).also {
                    it.action = TimerService.Actions.SKIP.toString()
                    context.startService(it)
                }
            }
            TimerActions.LapTimer -> {
                Intent(context, TimerService::class.java).also {
                    it.action = TimerService.Actions.LAP.toString()
                    context.startService(it)
                }
            }
            TimerActions.CompleteWorkout -> {
                Intent(context, TimerService::class.java).also {
                    it.action = TimerService.Actions.RESET.toString()
                    context.startService(it)
                }
            }
        }
    }

    override fun onCleared() {
        audioPlayer.release()
        super.onCleared()
    }

    actual val state: StateFlow<TimerState>
        get() = appContainer.timerState.asStateFlow()
}