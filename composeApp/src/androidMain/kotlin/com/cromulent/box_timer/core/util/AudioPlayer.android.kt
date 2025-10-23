package com.cromulent.box_timer.core.util

import android.content.Context
import android.media.MediaPlayer
import boxtimerpro.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.util.concurrent.ConcurrentHashMap

actual class AudioPlayer(private val context: Context) {
    // Preloaded MediaPlayer instances for instant playback
    private val preloadedPlayers = ConcurrentHashMap<String, MediaPlayer>()
    private val assetFileDescriptors =
        ConcurrentHashMap<String, android.content.res.AssetFileDescriptor>()

    init {
        // Preload common audio files for instant playback
        preloadCommonSounds()
    }

    @OptIn(ExperimentalResourceApi::class)
    private fun preloadCommonSounds() {
        try {
            val commonSounds = listOf(
                "files/beep.mp3",
                "files/bell-single.mp3",
                "files/bell-three-times.mp3",
                "files/whistle.mp3",
                "files/fight-deep-voice.mp3",
                "files/air-horn-long.mp3",
                "files/air-horn-short.mp3",
                "files/buzzer-long.mp3",
                "files/buzzer-short.mp3",
                "files/gun-shot.mp3",
                "files/pistol-shot.mp3",
                "files/Barcode-Scanner.mp3",
                "files/Clock-Beep.mp3",
                "files/Electric-Beep.mp3",
                "files/Drop.mp3",
                "files/Modern-Drop.mp3",
            )

            commonSounds.forEach { soundName ->
                try {
                    val assetPath = Res.getUri(soundName).removePrefix("file:///android_asset/")
                    val afd = context.assets.openFd(assetPath)
                    assetFileDescriptors[soundName] = afd

                    val player = MediaPlayer().apply {
                        setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                        prepare()
                        setOnCompletionListener {
                            // Reset position for next play
                            seekTo(0)
                        }
                    }
                    preloadedPlayers[soundName] = player
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    actual fun playSound(uri: String?) {
        if (uri == null) return

        try {
            // Check if this is a preloaded sound
            val soundName = uri.substringAfterLast("/")
            val preloadedPlayer = preloadedPlayers[soundName]

            if (preloadedPlayer != null) {
                // Use preloaded player for instant playback
                preloadedPlayer.start()
            } else {
                // Fallback for non-preloaded sounds
                playSoundFallback(uri)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    private fun playSoundFallback(uri: String) {
        try {
            val assetPath = Res.getUri(uri).removePrefix("file:///android_asset/")

            context.assets.openFd(assetPath).use { afd ->
                MediaPlayer().apply {
                    setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    setOnPreparedListener {
                        start()
                    }
                    setOnCompletionListener {
                        release()
                    }
                    prepareAsync() // Use async prepare for better performance
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Preload a specific audio file for instant playback
     * Call this when user changes audio settings
     */
    @OptIn(ExperimentalResourceApi::class)
    actual fun preloadSound(uri: String?) {
        if (uri == null) return

        val soundName = uri.substringAfterLast("/")
        if (preloadedPlayers.containsKey(soundName)) return // Already preloaded

        try {
            val assetPath = Res.getUri(uri).removePrefix("file:///android_asset/")
            val afd = context.assets.openFd(assetPath)
            assetFileDescriptors[soundName] = afd

            val player = MediaPlayer().apply {
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepare()
                setOnCompletionListener {
                    seekTo(0)
                }
            }
            preloadedPlayers[soundName] = player
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun release() {
        // Release all preloaded players
        preloadedPlayers.values.forEach { it.release() }
        preloadedPlayers.clear()

        // Close all asset file descriptors
        assetFileDescriptors.values.forEach { it.close() }
        assetFileDescriptors.clear()
    }
}