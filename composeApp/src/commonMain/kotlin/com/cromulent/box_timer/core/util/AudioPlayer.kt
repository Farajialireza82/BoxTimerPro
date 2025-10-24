package com.cromulent.box_timer.core.util

expect class AudioPlayer {
    fun playSound(uri: String?)
    fun preloadSound(uri: String?)
    fun stopEveryAudio()
    fun isAudioPlaying(): Boolean
    fun release()
}