package com.cromulent.box_timer.core.util

import android.content.Context
import android.media.MediaPlayer
import boxtimerpro.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

actual class AudioPlayer(private val context: Context) {
    private var mediaPlayer = MediaPlayer()

    @OptIn(ExperimentalResourceApi::class)
    actual fun playSound(uri: String?) {
        if (uri == null) return
        mediaPlayer = MediaPlayer()
        try {
            val assetPath = Res.getUri(uri).removePrefix("file:///android_asset/")
            val afd = context.assets.openFd(assetPath)

            mediaPlayer.apply {
                reset()
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepare()
                start()
            }
            afd.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun release() {
        mediaPlayer.release()
    }
}