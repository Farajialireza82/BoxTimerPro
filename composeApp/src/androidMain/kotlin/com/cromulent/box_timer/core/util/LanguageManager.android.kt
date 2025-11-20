package com.cromulent.box_timer.core.util

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.core.os.LocaleListCompat
import com.cromulent.box_timer.domain.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

actual class LanguageManager(
    val context: Context
) {
    private val _currentLanguage = MutableStateFlow(AppLanguage.SYSTEM)

    actual fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
        applyLanguageToSystem(language)
    }

    actual fun isRTL(): Boolean {
        val currentLang = _currentLanguage.value
        return when (currentLang) {
            AppLanguage.SYSTEM -> {
                getSystemLanguage().isRTL
            }
            else -> currentLang.isRTL
        }
    }
    
    private fun applyLanguageToSystem(language: AppLanguage) {
        val locale = when (language) {
            AppLanguage.SYSTEM -> {
                // Get the actual system language and apply it
                val systemLang = getSystemLanguage()
                systemLang.toLocale()
            }
            AppLanguage.ENGLISH -> Locale.ENGLISH
            AppLanguage.PERSIAN -> Locale("fa")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(locale.toLanguageTag())
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(
                    locale.toLanguageTag()
                )
            )
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
