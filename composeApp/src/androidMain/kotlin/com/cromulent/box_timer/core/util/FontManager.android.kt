package com.cromulent.box_timer.core.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.cromulent.box_timer.domain.AppLanguage
import java.util.Locale

actual class FontManager {
    actual fun getFontFamily(language: AppLanguage): FontFamily? {
        return when (language) {
            AppLanguage.PERSIAN -> createPersianFontFamily()
            AppLanguage.SYSTEM -> {
                // Check system language and apply appropriate font
                val systemLanguage = getSystemLanguage()
                if (systemLanguage == AppLanguage.PERSIAN) {
                    createPersianFontFamily()
                } else {
                    null // Use system default font
                }
            }
            else -> null
        }
    }
    
    private fun createPersianFontFamily(): FontFamily {
        return FontFamily(
            Font(
                resId = com.cromulent.box_timer.R.font.yekan_regular,
                weight = FontWeight.Normal
            ),
            Font(
                resId = com.cromulent.box_timer.R.font.yekan_medium,
                weight = FontWeight.Medium
            ),
            Font(
                resId = com.cromulent.box_timer.R.font.yekan_bold,
                weight = FontWeight.Bold
            )
        )
    }
    
    private fun getSystemLanguage(): AppLanguage {
        val systemLocale = Locale.getDefault()
        return when (systemLocale.language) {
            "fa" -> AppLanguage.PERSIAN
            "en" -> AppLanguage.ENGLISH
            else -> AppLanguage.ENGLISH // Default to English for unsupported languages
        }
    }
}
