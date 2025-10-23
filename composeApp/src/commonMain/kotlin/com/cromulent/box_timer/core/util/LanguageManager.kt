package com.cromulent.box_timer.core.util

import com.cromulent.box_timer.domain.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

expect class LanguageManager() {
    fun setLanguage(language: AppLanguage)
    fun getCurrentLanguage(): AppLanguage
    fun isRTL(): Boolean
    fun getFontFamily(): String?
    val currentLanguage: StateFlow<AppLanguage>
}
