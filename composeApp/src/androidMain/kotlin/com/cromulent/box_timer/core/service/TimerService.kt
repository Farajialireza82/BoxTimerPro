package com.cromulent.box_timer.core.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
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
import com.cromulent.box_timer.data.repository.SettingsRepositoryImpl
import com.cromulent.box_timer.data.repository.TimerRepositoryImpl
import com.cromulent.box_timer.domain.AppSettings
import com.cromulent.box_timer.domain.TimerSettings
import com.cromulent.box_timer.domain.timer.Lap
import com.cromulent.box_timer.presentation.theme.colorSchemes
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus
import com.cromulent.box_timer.presentation.timer_screen.TimerStatus.*
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
    private val settingsRepository = SettingsRepositoryImpl()
    var appSettings: AppSettings? = null


    private val appContainer by inject<AppContainer>()
    private val notificationManager by lazy { NotificationManagerCompat.from(this) }
    val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(applicationContext, "timer")
            .setSmallIcon(R.drawable.ic_avg_pace)
            .setColor(
                colorSchemes.firstOrNull { it.id == appSettings?.colorSchemeId }?.darkColorScheme?.primary?.toArgb()
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
    
    // Audio timing control - only for countdown audio to prevent rapid calls
    private var lastCountdownAudioTime = 0L
    private val minCountdownAudioInterval = 800L // Minimum 800ms between countdown audio calls
    private var lastCountdownAudioType = -1 // Track which countdown audio was last played

    lateinit var timerSettings: TimerSettings

    init {
        scope.launch {
            timerSettings = timerRepository.getTimerSettings()
            appSettings = settingsRepository.getAppSettings()
            _timerState.update {
                it.copy(
                    totalRounds = timerSettings.totalRounds,
                    remainingTime = timerSettings.roundDuration,
                    roundDuration = timerSettings.roundDuration
                )
            }
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        if (appSettings?.stopTimerOnClose == true) {
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

            Actions.SKIP.toString() -> {
                skipTimer()
            }
            Actions.LAP.toString() -> {
                addLap()
            }

        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun addLap(){

        val lap = Lap(
            roundNumber = _timerState.value.currentRound,
            createTime = _timerState.value.remainingTime
        )

        _timerState.update {
            it.copy(
                laps = listOf(lap) + it.laps
            )
        }
    }

    private fun skipTimer() {
        scope.launch {
            if(timerState.value.timerStatus == Paused){
                toggleTimer()
            }
            handlePhaseComplete()
        }
    }

    private fun toggleTimer() {
        val currentStatus = timerState.value.timerStatus

        if (currentStatus.isInActiveState()) {
            // Pause - save what we were doing before pausing
            println("TimerService: Pausing timer, stopping audio...")
            audioPlayer.stopEveryAudio()
            println("TimerService: Audio stopped, isPlaying: ${audioPlayer.isAudioPlaying()}")
            dontKeepScreenOn()
            statusBeforePause = currentStatus
            _timerState.update { it.copy(timerStatus = Paused) }
            pauseStartTime = SystemClock.elapsedRealtime()
        } else {
            isRunning = true
            keepScreenOn()
            // Resume/Start
            if (pauseStartTime != 0L) {
                totalPauseDuration += SystemClock.elapsedRealtime() - pauseStartTime
            }

            val nextStatus = when {
                currentStatus == Ready -> Running
                currentStatus == Paused && statusBeforePause != null -> statusBeforePause!!
                else -> Running // Fallback
            }

            _timerState.update { it.copy(timerStatus = nextStatus) }



            scope.launch {
                timerState.value.let {
                    if (it.currentRound == 1 && it.remainingTime == timerSettings.roundDuration) {
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
            if (currentStatus == Paused) {
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
                Resting -> timerSettings.restDuration
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
                countDownAlert(3) // 3-second countdown
            }

            if (remainingTime < 2000 && countdownTwoAudioPlayed.not()) {
                countdownTwoAudioPlayed = true
                countDownAlert(2) // 2-second countdown
            }

            if (remainingTime < 1000 && countdownThreeAudioPlayed.not()) {
                countdownThreeAudioPlayed = true
                countDownAlert(1) // 1-second countdown
            }

            // Phase complete
            if (remainingTime < 0) {
                handlePhaseComplete()
                if (!timerState.value.timerStatus.isInActiveState()) break
            }

            delay(10L) // Keep fast updates for smooth progress indicator

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
                    timerStatus = CountDown,
                    countDownText = getString(R.string.countdown_get_ready) + " ${4 - i}",
                )
            }
            countDownAlert(4 - i) // Pass the countdown number (3, 2, 1)
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

        // Reset audio timing for new phase
        lastCountdownAudioTime = 0L
        lastCountdownAudioType = -1

        //round ended - play start round audio
        if (currentStatus == Running) endRoundAlert()


        when (currentStatus) {
            Running -> {
                // End of round - check if it was the last round
                if (timerState.value.currentRound >= timerSettings.totalRounds) {
                    // All rounds completed - show completion dialog
                    _timerState.update { it.copy(timerStatus = Completed) }
//                    endWorkoutAlert()
                } else {
                    // Transition to rest
                    _timerState.update { it.copy(timerStatus = Resting) }
                }
            }

            Resting -> {
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
        dontKeepScreenOn()

        // Reset audio timing and flags
        lastCountdownAudioTime = 0L
        lastCountdownAudioType = -1
        countDownOneAudioPlayed = false
        countdownTwoAudioPlayed = false
        countdownThreeAudioPlayed = false

        _timerState.update {
            it.copy(
                remainingTime = timerSettings.roundDuration,
                progress = 0f,
                currentRound = 1,
                totalRounds = timerSettings.totalRounds,
                timerStatus = Ready,
                laps = emptyList()
            )
        }
    }

    @SuppressLint("MissingPermission") // We check for the permission when pressing the Play button in the UI
    fun showTimerNotification() {

        val currentStatus = timerState.value.timerStatus
        val currentStatusMessage = currentStatus.getMessageString()
        val remainingTime = timerState.value.remainingTime
        val countdownText = timerState.value.countDownText
        val remainingTimeString = formatTime(remainingTime)

        if (currentStatus == CountDown) {
            notificationManager.notify(
                1,
                notificationBuilder
                    .setContentTitle(countdownText)
                    .addRestAction(applicationContext)
                    .build()
            )
            return
        }

        if (currentStatus == Completed) {
            notificationManager.notify(
                1,
                notificationBuilder
                    .setContentTitle(getString(R.string.notification_workout_complete_title))
                    .setContentText(getString(R.string.notification_workout_complete_text))
                    .addRestAction(applicationContext)
                    .build()
            )
            return
        }

        notificationManager.notify(
            1,
            notificationBuilder
                .setContentTitle(
                    "$currentStatusMessage $middleDot $remainingTimeString"
                )
                .addTimerActions(applicationContext, isPaused = (currentStatus == Paused))
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
        playAudio(appSettings?.endRoundAudioFile?.uri)
    }

    private fun endWorkoutAlert() {
//        vibratePhone(2000L)
//        playAudio(appSettings?.endRoundAudioFile?.uri)
    }

    private fun startRoundAlert() {
        vibratePhone(700L)
        playAudio(appSettings?.startRoundAudioFile?.uri)
    }

    private fun countDownAlert(countdownType: Int = 0) {
        vibratePhone(500L)
        playCountdownAudio(appSettings?.countDownAudioFile?.uri, countdownType)
    }

    private fun vibratePhone(duration: Long = 1000L) {
        if (appSettings?.isVibrationEnabled == true) systemEngine.vibrate(duration)
    }

    private fun playAudio(uri: String?) {
        if (appSettings?.muteAllSounds == true) return
        audioPlayer.playSound(uri)
    }

    private fun playCountdownAudio(uri: String?, countdownType: Int) {
        if (appSettings?.muteAllSounds == true) return

        val currentTime = SystemClock.elapsedRealtime()

        // Allow countdown audio if:
        // 1. No audio is currently playing, OR
        // 2. It's been more than 800ms since last countdown audio, OR  
        // 3. This is a different countdown type (3s, 2s, 1s)
        val canPlayAudio = !audioPlayer.isAudioPlaying() ||
                (currentTime - lastCountdownAudioTime >= minCountdownAudioInterval) ||
                (lastCountdownAudioType != countdownType)

        if (!canPlayAudio) {
            return // Skip if conditions not met
        }

        lastCountdownAudioTime = currentTime
        lastCountdownAudioType = countdownType
        audioPlayer.playSound(uri)
    }

    private fun keepScreenOn() {
        if (appSettings?.keepScreenOnEnabled == true) {
            systemEngine.keepScreenOn(true)
        }
    }

    private fun dontKeepScreenOn() {
        systemEngine.keepScreenOn(false)
    }

    companion object {
        var isRunning = false
            private set
    }

    enum class Actions {
        TOGGLE, RESET, SKIP, LAP
    }

    fun TimerStatus.getMessageString(): String {
        val messageRes = when (this) {
            Ready -> R.string.title_ready
            Running -> R.string.title_fight
            Paused -> R.string.title_paused
            Resting -> R.string.title_rest
            CountDown -> R.string.title_counting_down
            Completed -> R.string.title_workout_complete
        }
        return applicationContext.getString(messageRes)
    }
}