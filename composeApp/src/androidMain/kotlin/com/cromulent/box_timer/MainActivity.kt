package com.cromulent.box_timer

import android.app.Activity
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
import androidx.compose.runtime.SideEffect
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
import com.cromulent.box_timer.presentation.theme.BoxTimerProTheme
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

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

        loadKoinModules(module{
            single { this@MainActivity as Activity }
        })

        setContent {
            BoxTimerProTheme {
                val view = LocalView.current
                val colorScheme = MaterialTheme.colorScheme
                
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = colorScheme.background.toArgb()
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                }
                
                App()
            }
        }
        Thread {
            Thread.sleep(500)
            keepSplashScreenOn = false
        }.start()
    }
}