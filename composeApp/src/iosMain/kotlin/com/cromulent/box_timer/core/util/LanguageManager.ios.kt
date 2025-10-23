package com.cromulent.box_timer.core.util

import com.cromulent.box_timer.domain.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

actual class LanguageManager {
    private val _currentLanguage = MutableStateFlow(AppLanguage.SYSTEM)
    actual val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    actual fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
        // For iOS, we would need to implement proper locale switching
        // This is a placeholder implementation
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
}

fun getSystemLanguage(): AppLanguage {
    // For iOS, we'll default to English for now
    // In a real implementation, you would get the system language from NSLocale
    return AppLanguage.ENGLISH
}
