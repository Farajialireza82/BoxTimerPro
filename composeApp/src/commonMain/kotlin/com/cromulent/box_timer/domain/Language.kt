package com.cromulent.box_timer.domain

import kotlinx.serialization.Serializable

@Serializable
enum class AppLanguage(
    val localeCode: String,
    val displayName: String,
    val isRTL: Boolean = false,
    val fontFamily: String? = null
) {
    SYSTEM("system", "Follow System Settings", false, null),
    ENGLISH("en", "English"),
    PERSIAN("fa", "فارسی", isRTL = true, fontFamily = "IRANYekanX")
}

fun AppLanguage.getDisplayNameForLanguage(language: AppLanguage): String {
    return when (this) {
        AppLanguage.SYSTEM -> when (language) {
            AppLanguage.ENGLISH -> "Follow System Settings"
            AppLanguage.PERSIAN -> "پیروی از تنظیمات سیستم"
            AppLanguage.SYSTEM -> "Follow System Settings"
        }
        AppLanguage.ENGLISH -> "English"
        AppLanguage.PERSIAN -> "فارسی"
    }
}

