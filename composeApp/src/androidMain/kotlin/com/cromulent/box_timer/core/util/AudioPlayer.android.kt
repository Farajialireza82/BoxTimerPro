package com.cromulent.box_timer.core.util

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import boxtimerpro.composeapp.generated.resources.Res

actual class AudioPlayer(context: Context){
    private val mediaPlayer = ExoPlayer.Builder(context).build()

    init {
        mediaPlayer.prepare()
    }

    actual fun playSound(uri: String) {
        mediaPlayer.setMediaItem(MediaItem.fromUri(Res.getUri(uri)))
        mediaPlayer.play()
    }

    actual fun release(){
        mediaPlayer.release()
    }

}