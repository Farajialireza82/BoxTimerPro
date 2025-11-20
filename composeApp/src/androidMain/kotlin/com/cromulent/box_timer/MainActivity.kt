package com.cromulent.box_timer

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.cromulent.box_timer.core.app.App
import com.cromulent.box_timer.BuildKonfig.YANDEX_METRICA_API_KEY
import com.cromulent.box_timer.core.util.LanguageManager
import com.cromulent.box_timer.domain.AppLanguage
import com.cromulent.box_timer.domain.SettingsRepository
import com.cromulent.box_timer.presentation.settings_screen.SettingsViewModel
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = AppMetricaConfig.newConfigBuilder(YANDEX_METRICA_API_KEY).build()
        AppMetrica.activate(this, config)

        var keepSplashScreenOn = true
        installSplashScreen().apply {
            keepSplashScreenOn
        }
        enableEdgeToEdge()

        loadKoinModules(module {
            single { this@MainActivity as Activity }
        })

        // Apply saved language setting immediately before UI is rendered
        val settingsRepository by inject<SettingsRepository>()
        val languageManager by inject<LanguageManager>()

        // Get the saved language setting synchronously
        val savedSettings = runBlocking { settingsRepository.getAppSettings() }
        savedSettings.selectedLanguage?.let { language ->
            languageManager.setLanguage(language)
        }

        setContent {
            val setting by settingsRepository.appSettings.collectAsState(null)

            BoxTimerProTheme {
                val view = LocalView.current

                SideEffect {
                    val window = (view.context as Activity).window
                    WindowCompat.getInsetsController(window, view).apply {
                        isAppearanceLightStatusBars = (setting?.isDarkMode == false)
                        isAppearanceLightNavigationBars = (setting?.isDarkMode == false)
                    }
                }
                
                // Apply text direction based on app language, not system language
                val textDirection = if (languageManager.isRTL()) {
                    LayoutDirection.Rtl
                } else {
                    LayoutDirection.Ltr
                }
                
                CompositionLocalProvider(
                    LocalLayoutDirection provides textDirection
                ) {
                    App()
                }
            }
        }
        Thread {
            Thread.sleep(500)
            keepSplashScreenOn = false
        }.start()
    }
}