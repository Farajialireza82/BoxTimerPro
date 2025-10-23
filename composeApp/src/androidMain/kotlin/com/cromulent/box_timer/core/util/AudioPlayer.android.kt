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

        try {
            val assetPath = Res.getUri(uri).removePrefix("file:///android_asset/")

            context.assets.openFd(assetPath).use { afd ->
                mediaPlayer.apply {
                    reset()
                    setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    prepare() // Move prepare here, after setDataSource
                    start()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun release() {
        mediaPlayer.release()
    }
}