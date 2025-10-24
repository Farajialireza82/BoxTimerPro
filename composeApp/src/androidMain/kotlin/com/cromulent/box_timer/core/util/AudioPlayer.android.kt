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
    
    // Track currently playing audio to prevent overlaps
    private var currentPlayingSound: String? = null
    private var mIsPlaying = false
    private var fallbackPlayer: MediaPlayer? = null

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
                            // Reset position for next play and mark as not playing
                            seekTo(0)
                            mIsPlaying = false
                            currentPlayingSound = null
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
            val soundName = uri.substringAfterLast("/")
            
            // If the same sound is already playing, don't interrupt it
            if (mIsPlaying && currentPlayingSound == soundName) {
                return
            }
            
            // Stop current audio if different sound is requested
            if (mIsPlaying && currentPlayingSound != soundName) {
                stopCurrentAudio()
            }

            val preloadedPlayer = preloadedPlayers[soundName]

            if (preloadedPlayer != null) {
                // Use preloaded player for instant playback
                currentPlayingSound = soundName
                mIsPlaying = true
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
            val soundName = uri.substringAfterLast("/")

            // Stop any existing fallback player
            fallbackPlayer?.let { player ->
                if (player.isPlaying) {
                    player.stop()
                }
                player.release()
            }

            context.assets.openFd(assetPath).use { afd ->
                fallbackPlayer = MediaPlayer().apply {
                    setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                    setOnPreparedListener {
                        currentPlayingSound = soundName
                        mIsPlaying = true
                        start()
                    }
                    setOnCompletionListener {
                        mIsPlaying = false
                        currentPlayingSound = null
                        release()
                        fallbackPlayer = null
                    }
                    prepareAsync() // Use async prepare for better performance
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun stopCurrentAudio() {
        currentPlayingSound?.let { soundName ->
            preloadedPlayers[soundName]?.let { player ->
                if (player.isPlaying) {
                    player.pause()
                    player.seekTo(0)
                }
            }
        }
        mIsPlaying = false
        currentPlayingSound = null
    }
    
    actual fun stopEveryAudio() {
        println("AudioPlayer: stopEveryAudio called, currentPlayingSound: $currentPlayingSound, mIsPlaying: $mIsPlaying")
        
        // Stop all preloaded players more aggressively
        preloadedPlayers.values.forEach { player ->
            try {
                if (player.isPlaying) {
                    println("AudioPlayer: Stopping preloaded player that is currently playing")
                    player.stop() // Use stop() instead of pause() for immediate stopping
                    player.prepare() // Re-prepare after stopping
                    player.seekTo(0)
                }
            } catch (e: Exception) {
                // Handle any exceptions when stopping
                println("AudioPlayer: Exception while stopping preloaded player: ${e.message}")
                e.printStackTrace()
            }
        }
        
        // Stop fallback player if it exists
        fallbackPlayer?.let { player ->
            try {
                if (player.isPlaying) {
                    println("AudioPlayer: Stopping fallback player that is currently playing")
                    player.stop()
                }
                player.release()
                fallbackPlayer = null
            } catch (e: Exception) {
                println("AudioPlayer: Exception while stopping fallback player: ${e.message}")
                e.printStackTrace()
            }
        }
        
        mIsPlaying = false
        currentPlayingSound = null
        println("AudioPlayer: stopEveryAudio completed")
    }
    
    actual fun isAudioPlaying(): Boolean {
        val preloadedPlaying = preloadedPlayers.values.any { it.isPlaying }
        val fallbackPlaying = fallbackPlayer?.isPlaying ?: false
        return mIsPlaying && (preloadedPlaying || fallbackPlaying)
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
                    mIsPlaying = false
                    currentPlayingSound = null
                }
            }
            preloadedPlayers[soundName] = player
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun release() {
        // Stop current audio
        stopCurrentAudio()
        
        // Release fallback player
        fallbackPlayer?.let { player ->
            if (player.isPlaying) {
                player.stop()
            }
            player.release()
            fallbackPlayer = null
        }
        
        // Release all preloaded players
        preloadedPlayers.values.forEach { it.release() }
        preloadedPlayers.clear()

        // Close all asset file descriptors
        assetFileDescriptors.values.forEach { it.close() }
        assetFileDescriptors.clear()
    }
}