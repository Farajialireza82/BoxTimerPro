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
            AppLanguage.SYSTEM -> getSystemLanguage().toLocale()
            AppLanguage.ENGLISH -> Locale.ENGLISH
            AppLanguage.PERSIAN -> Locale("fa")
        }
        
        Locale.setDefault(locale)
        
        // Apply locale to the current activity's configuration
        activity?.let { act ->
            val config = Configuration(act.resources.configuration)
            config.setLocale(locale)
            act.resources.updateConfiguration(config, act.resources.displayMetrics)
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
    val systemLocale = Locale.getDefault()
    
    return when (systemLocale.language) {
        "fa" -> AppLanguage.PERSIAN
        "en" -> AppLanguage.ENGLISH
        else -> AppLanguage.ENGLISH // Default to English for unsupported languages
    }
}
