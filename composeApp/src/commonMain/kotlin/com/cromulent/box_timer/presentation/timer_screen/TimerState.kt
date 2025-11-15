package com.cromulent.box_timer.presentation.timer_screen

import com.cromulent.box_timer.domain.timer.Lap

data class TimerState(
    val timerStatus: TimerStatus = TimerStatus.Ready,
    val remainingTime: Long = 0L,
    val progress: Float = 0f,
    val currentRound: Int = 1,
    val totalRounds: Int = 9,
    val countDownText: String = "",
    val laps: List<Lap> = emptyList(),
    val roundDuration: Long = 0L
)

enum class TimerStatus(val messageKey: String){
    Ready("title_ready"), 
    Running("title_fight"), 
    Paused("title_paused"), 
    Resting("title_rest"), 
    CountDown("title_counting_down"), 
    Completed("title_workout_complete")
}
fun TimerStatus.isInActiveState(): Boolean{
    return this == TimerStatus.Running || this == TimerStatus.Resting
}

fun TimerState.isInActiveState(): Boolean = timerStatus.isInActiveState()