package com.cromulent.box_timer.core.util

import boxtimerpro.composeapp.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL
import platform.posix.err

actual class AudioPlayer {

    @OptIn(ExperimentalForeignApi::class)
    actual fun playSound(uri: String) {
        val avAudioPlayer = AVAudioPlayer(NSURL.URLWithString(Res.getUri(uri))!!, error = null)
        avAudioPlayer.prepareToPlay()
        avAudioPlayer.play()
    }

    actual fun release() {}
}