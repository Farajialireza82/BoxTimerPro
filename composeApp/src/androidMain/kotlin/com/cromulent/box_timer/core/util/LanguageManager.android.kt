package com.cromulent.box_timer.core.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.cromulent.box_timer.domain.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

actual class LanguageManager {
    private val _currentLanguage = MutableStateFlow(AppLanguage.SYSTEM)
    actual val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()
    private var activity: Activity? = null

    fun setActivity(activity: Activity) {
        this.activity = activity
    }

    actual fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
        applyLanguageToSystem(language)
    }

    actual fun getCurrentLanguage(): AppLanguage {
        return _currentLanguage.value
    }
    
    actual fun isRTL(): Boolean {
        val currentLang = _currentLanguage.value
        return when (currentLang) {
            AppLanguage.SYSTEM -> {
                // For system, check the actual system language
                val systemLang = getSystemLanguage()
                systemLang.isRTL
            }
            else -> currentLang.isRTL
        }
    }
    
    actual fun getFontFamily(): String? {
        val currentLang = _currentLanguage.value
        return when (currentLang) {
            AppLanguage.SYSTEM -> {
                // For system, check the actual system language
                val systemLang = getSystemLanguage()
                systemLang.fontFamily
            }
            else -> currentLang.fontFamily
        }
    }
    
    private fun applyLanguageToSystem(language: AppLanguage) {
        val locale = when (language) {
            AppLanguage.SYSTEM -> {
                // Get the actual system language and apply it
                val systemLang = getSystemLanguage()
                println("DEBUG: System language detected as: ${systemLang}")
                systemLang.toLocale()
            }
            AppLanguage.ENGLISH -> Locale.ENGLISH
            AppLanguage.PERSIAN -> Locale("fa")
        }
        
        println("DEBUG: Applying locale: ${locale}")
        Locale.setDefault(locale)
        
        // Apply locale to the current activity's configuration
        activity?.let { act ->
            val config = Configuration(act.resources.configuration)
            config.setLocale(locale)
            
            // Apply the configuration to the activity's resources
            @Suppress("DEPRECATION")
            act.resources.updateConfiguration(config, act.resources.displayMetrics)
            
            // Also update the application context
            val appContext = act.applicationContext
            val appConfig = Configuration(appContext.resources.configuration)
            appConfig.setLocale(locale)
            @Suppress("DEPRECATION")
            appContext.resources.updateConfiguration(appConfig, appContext.resources.displayMetrics)
            
            println("DEBUG: Language applied successfully")
        }
    }
    
    private fun AppLanguage.toLocale(): Locale {
        return when (this) {
            AppLanguage.SYSTEM -> getSystemLanguage().toLocale()
            AppLanguage.ENGLISH -> Locale.ENGLISH
            AppLanguage.PERSIAN -> Locale("fa")
        }
    }
}

val LocalLanguageManager = compositionLocalOf<LanguageManager> { 
    error("LanguageManager not provided") 
}

@Composable
fun ProvideLanguageManager(
    languageManager: LanguageManager,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalLanguageManager provides languageManager) {
        content()
    }
}

fun getSystemLanguage(): AppLanguage {
    // Get the actual system language using a more reliable method
    val systemLocale = try {
        // Use Resources.getSystem() to get the system resources
        val systemResources = android.content.res.Resources.getSystem()
        val systemConfig = systemResources.configuration
        val detectedLocale = systemConfig.locales[0] ?: Locale.getDefault()
        println("DEBUG: System locale detected: ${detectedLocale.language}")
        detectedLocale
    } catch (e: Exception) {
        println("DEBUG: Exception getting system locale: ${e.message}")
        // Fallback to Locale.getDefault()
        Locale.getDefault()
    }
    
    val result = when (systemLocale.language) {
        "fa" -> AppLanguage.PERSIAN
        "en" -> AppLanguage.ENGLISH
        else -> AppLanguage.ENGLISH // Default to English for unsupported languages
    }
    
    println("DEBUG: System language result: ${result}")
    return result
}
