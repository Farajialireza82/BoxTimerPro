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
    return AppLanguage.ENGLISH
}
