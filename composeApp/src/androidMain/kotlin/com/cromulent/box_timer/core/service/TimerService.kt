package com.cromulent.box_timer.core.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.cromulent.box_timer.R
import com.cromulent.box_timer.core.util.AudioPlayer
import com.cromulent.box_timer.core.util.SystemEngine
import com.cromulent.box_timer.core.util.formatTime
import com.cromulent.box_timer.data.AppContainer
import com.cromulent.box_timer.data.repository.TimerRepositoryImpl
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.presentation.theme.colorSchemes
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.Running
import com.cromulent.box_timer.presentation.timer_screen.isInActiveState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.text.Typography.middleDot

class TimerService : Service() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private val settingsRepository: SettingsRepository by inject()
    lateinit var appSettings: AppSettings


    private val appContainer by inject<AppContainer>()
    private val notificationManager by lazy { NotificationManagerCompat.from(this) }
    val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(applicationContext, "timer")
            .setSmallIcon(R.drawable.ic_avg_pace)
            .setColor(
                colorSchemes.firstOrNull { it.id == appSettings.colorSchemeId }?.darkColorScheme?.primary?.toArgb()
                    ?: Color.Blue.toArgb()
            )
            .setContentIntent(
                PendingIntent.getActivity(
                    applicationContext,
                    0,
                    applicationContext.packageManager.getLaunchIntentForPackage(applicationContext.packageName),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setShowWhen(true)
            .setSilent(true)
            .setOngoing(true)
    }

    private val audioPlayer: AudioPlayer by inject()
    private val systemEngine: SystemEngine by inject()

    private val _timerState by lazy { appContainer.timerState }
    private val timerState by lazy { _timerState.asStateFlow() }
    private val timerRepository = TimerRepositoryImpl()

    private var phaseStartTime = 0L
    private var pauseStartTime = 0L
    private var totalPauseDuration = 0L

    private var countdownThreeAudioPlayed: Boolean = false
    private var countdownTwoAudioPlayed: Boolean = false
    private var countDownOneAudioPlayed: Boolean = false

    private var statusBeforePause: TimerStatus? = null

    lateinit var timerSettings: TimerSettings

    init {
        scope.launch {
            timerSettings = timerRepository.getTimerSettings()
            appSettings = settingsRepository.getAppSettings()
            _timerState.update {
                it.copy(
                    totalRounds = timerSettings.totalRounds,
                    remainingTime = timerSettings.roundDuration
                )
            }
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        if (appSettings.stopTimerOnClose) {
            resetTimer()
            stopForegroundService()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        job.cancel()
        notificationManager.cancel(1)
        resetTimer()
        isRunning = false
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.TOGGLE.toString() -> {
                startForegroundService()
                toggleTimer()
            }

            Actions.RESET.toString() -> {
                if (timerState.value.timerStatus.isInActiveState()) toggleTimer()
                scope.launch {
                    resetTimer()
                    stopForegroundService()
                }
            }

        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun toggleTimer() {
        val currentStatus = timerState.value.timerStatus

        if (currentStatus.isInActiveState()) {
            // Pause - save what we were doing before pausing
            statusBeforePause = currentStatus
            _timerState.update { it.copy(timerStatus = TimerStatus.Paused) }
            pauseStartTime = SystemClock.elapsedRealtime()
        } else {
            isRunning = true
            // Resume/Start
            if (pauseStartTime != 0L) {
                totalPauseDuration += SystemClock.elapsedRealtime() - pauseStartTime
            }

            val nextStatus = when {
                currentStatus == TimerStatus.Ready -> Running
                currentStatus == TimerStatus.Paused && statusBeforePause != null -> statusBeforePause!!
                else -> Running // Fallback
            }

            _timerState.update { it.copy(timerStatus = nextStatus) }



            scope.launch {
                timerState.value.let {
                    if(it.currentRound == 1 && it.remainingTime == timerSettings.roundDuration){
                        runCountDown()
                    }
                }
                runTimerLoop()
            }
        }
        showTimerNotification()
    }

    private var lastNotificationTime = 0L

    private suspend fun runTimerLoop() {
        while (timerState.value.timerStatus.isInActiveState()) {
            val currentStatus = timerState.value.timerStatus

            // Exit immediately if paused
            if (currentStatus == TimerStatus.Paused) {
                break
            }

            // Initialize phase start time on first iteration
            if (phaseStartTime == 0L) {
                //new round started - play start round audio
                if (currentStatus == Running) startRoundAlert()
                phaseStartTime = SystemClock.elapsedRealtime()
            }

            val phaseDuration = when (currentStatus) {
                Running -> timerSettings.roundDuration
                TimerStatus.Resting -> timerSettings.restDuration
                else -> 0
            }

            val elapsed = SystemClock.elapsedRealtime() - phaseStartTime - totalPauseDuration
            val remainingTime = phaseDuration - elapsed.toInt()
            val progress = elapsed.toFloat() / phaseDuration.toFloat()

            _timerState.update {
                it.copy(
                    remainingTime = remainingTime,
                    progress = progress
                )
            }

            if (remainingTime < 3000 && countDownOneAudioPlayed.not()) {
                countDownOneAudioPlayed = true
                countDownAlert()
            }

            if (remainingTime < 2000 && countdownTwoAudioPlayed.not()) {
                countdownTwoAudioPlayed = true
                countDownAlert()
            }

            if (remainingTime < 1000 && countdownThreeAudioPlayed.not()) {
                countdownThreeAudioPlayed = true
                countDownAlert()
            }

            // Phase complete
            if (remainingTime < 0) {
                handlePhaseComplete()
                if (!timerState.value.timerStatus.isInActiveState()) break
            }

            delay(10L)
            
            // Only show notification once per second to avoid race conditions
            val currentTime = SystemClock.elapsedRealtime()
            if (currentTime - lastNotificationTime >= 900L) {
                showTimerNotification()
                lastNotificationTime = currentTime
            }
        }
    }

    private suspend fun runCountDown() {
        val beforeStatus = timerState.value.timerStatus
        for (i in 1..3) {
            _timerState.update {
                it.copy(
                    timerStatus = TimerStatus.CountDown,
                    countDownText = "Get Ready: ${4 - i}",
                )
            }
            countDownAlert()
            showTimerNotification()
            delay(1000L)
        }
        _timerState.update {
            it.copy(
                timerStatus = beforeStatus,
                countDownText = ""
            )
        }

    }

    private suspend fun handlePhaseComplete() {
        val currentStatus = timerState.value.timerStatus

        phaseStartTime = 0L
        pauseStartTime = 0L
        totalPauseDuration = 0L

        countDownOneAudioPlayed = false
        countdownTwoAudioPlayed = false
        countdownThreeAudioPlayed = false

        //round ended - play start round audio
        if (currentStatus == Running) endRoundAlert()


        when (currentStatus) {
            Running -> {
                // End of round - check if it was the last round
                if (timerState.value.currentRound >= timerSettings.totalRounds) {
                    resetTimer()
                } else {
                    // Transition to rest
                    _timerState.update { it.copy(timerStatus = TimerStatus.Resting) }
                }
            }

            TimerStatus.Resting -> {
                // End of rest - start next round
                _timerState.update {
                    it.copy(
                        currentRound = it.currentRound + 1,
                        timerStatus = Running
                    )
                }
            }

            else -> {}
        }
    }

    private fun resetTimer() {
        phaseStartTime = 0L
        pauseStartTime = 0L
        totalPauseDuration = 0L
        isRunning = false

        _timerState.update {
            it.copy(
                remainingTime = timerSettings.roundDuration,
                progress = 0f,
                currentRound = 1,
                totalRounds = timerSettings.totalRounds,
                timerStatus = TimerStatus.Ready
            )
        }
    }

    @SuppressLint("MissingPermission") // We check for the permission when pressing the Play button in the UI
    fun showTimerNotification() {

        val currentStatus = timerState.value.timerStatus
        val currentStatusMessage = currentStatus.message
        val remainingTime = timerState.value.remainingTime
        val countdownText = timerState.value.countDownText
        val remainingTimeString = formatTime(remainingTime)

        notificationManager.notify(
            1,
            notificationBuilder
                .setContentTitle(
                    if(currentStatus == TimerStatus.CountDown) countdownText
                    else "$currentStatusMessage $middleDot $remainingTimeString remaining"
                )
                .addTimerActions(applicationContext, isPaused = (currentStatus == TimerStatus.Paused))
                .build()
        )
    }

    private fun startForegroundService() {
        startForeground(1, notificationBuilder.build())
        isRunning = true
    }

    private fun stopForegroundService() {
        notificationManager.cancel(1)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        isRunning = false
    }

    private fun endRoundAlert() {
        vibratePhone(1000L)
        playAudio(appSettings.endRoundAudioFile.uri)
    }

    private fun startRoundAlert() {
        vibratePhone(700L)
        playAudio(appSettings.startRoundAudioFile.uri)
    }

    private fun countDownAlert() {
        vibratePhone(500L)
        playAudio(appSettings.countDownAudioFile.uri)
    }

    private fun vibratePhone(duration: Long = 1000L) {
        if (appSettings.isVibrationEnabled) systemEngine.vibrate(duration)
    }

    private fun playAudio(uri: String?) {
        if (appSettings.muteAllSounds) return
        audioPlayer.playSound(uri)
    }

    companion object {
        var isRunning = false
            private set
    }

    enum class Actions {
        TOGGLE, RESET
    }
}