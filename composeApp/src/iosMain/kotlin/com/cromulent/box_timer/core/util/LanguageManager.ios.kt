package com.cromulent.box_timer.core.util

import com.cromulent.box_timer.domain.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

actual class LanguageManager {
    private val _currentLanguage = MutableStateFlow(AppLanguage.SYSTEM)

    actual fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
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

}

fun getSystemLanguage(): AppLanguage {
    // For iOS, we'll default to English for now
    // In a real implementation, you would get the system language from NSLocale
    return AppLanguage.ENGLISH
}
