package com.cromulent.box_timer.core.util

import androidx.compose.ui.text.font.FontFamily
import com.cromulent.box_timer.domain.AppLanguage

expect class FontManager() {
    fun getFontFamily(language: AppLanguage): FontFamily?
}
