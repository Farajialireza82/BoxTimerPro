package com.cromulent.box_timer.presentation.timer_screen

import androidx.lifecycle.ViewModel
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.domain.SettingsRepository
import kotlinx.coroutines.flow.StateFlow

expect class TimerViewModel: ViewModel {

    val state: StateFlow<TimerState>

    fun onAction(action: TimerActions)

}