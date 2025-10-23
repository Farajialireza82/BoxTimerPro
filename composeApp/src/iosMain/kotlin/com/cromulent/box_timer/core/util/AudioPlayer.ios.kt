package com.cromulent.box_timer.core.util

import boxtimerpro.composeapp.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL
import platform.posix.err

actual class AudioPlayer {

    private var avAudioPlayer: AVAudioPlayer? = null

    @OptIn(ExperimentalForeignApi::class)
    actual fun playSound(uri: String?) {
        if (uri == null) return
        val url = NSURL.URLWithString(Res.getUri(uri))!!
        avAudioPlayer = AVAudioPlayer(url, error = null)
        avAudioPlayer?.prepareToPlay()
        avAudioPlayer?.play()
    }

    actual fun preloadSound(uri: String?) {
        // iOS doesn't need preloading as AVAudioPlayer handles it efficiently
        // This is a no-op for iOS
    }

    actual fun release() {
        avAudioPlayer?.stop()
        avAudioPlayer = null
    }
}