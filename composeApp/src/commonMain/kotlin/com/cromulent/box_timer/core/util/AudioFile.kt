package com.cromulent.box_timer.core.util

import kotlinx.serialization.Serializable

@Serializable
data class AudioFile(
    val title: String,
    val uri: String?
)
