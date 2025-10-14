package com.cromulent.box_timer

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cromulent.box_timer.core.app.App
import com.cromulent.box_timer.BuildKonfig.YANDEX_METRICA_API_KEY
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
            App()
        }
        Thread {
            Thread.sleep(500)
            keepSplashScreenOn = false
        }.start()
    }
}