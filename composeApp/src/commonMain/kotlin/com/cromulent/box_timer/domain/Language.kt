package com.cromulent.box_timer.domain

import boxtimerpro.composeapp.generated.resources.Res
import boxtimerpro.composeapp.generated.resources.language_english
import boxtimerpro.composeapp.generated.resources.language_persian
import boxtimerpro.composeapp.generated.resources.language_settings_title
import boxtimerpro.composeapp.generated.resources.language_system
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

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

fun AppLanguage.getDisplayNameResForLanguage(): StringResource {
    return when (this) {
        AppLanguage.SYSTEM -> Res.string.language_system
        AppLanguage.ENGLISH -> Res.string.language_english
        AppLanguage.PERSIAN -> Res.string.language_persian
    }
}

