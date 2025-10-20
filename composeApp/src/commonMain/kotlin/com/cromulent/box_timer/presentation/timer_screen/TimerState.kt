package com.cromulent.box_timer.presentation.timer_screen


data class TimerState(
    val timerStatus: TimerStatus = TimerStatus.Ready,
    val remainingTime: Long = 0L,
    val progress: Float = 0f,
    val currentRound: Int = 1,
    val totalRounds: Int = 9,
)

enum class TimerStatus(val message: String){
    Ready("Ready"), Running("FIGHT"), Paused("Paused"), Resting("Rest")
}
fun TimerStatus.isInActiveState(): Boolean{
    return this == TimerStatus.Running || this == TimerStatus.Resting
}

fun TimerState.isInActiveState(): Boolean = timerStatus.isInActiveState()