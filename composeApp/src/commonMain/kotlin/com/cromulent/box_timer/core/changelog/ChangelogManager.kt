package com.cromulent.box_timer.core.changelog

import com.russhwolf.settings.Settings

class ChangelogManager(
    private val settings: Settings
) {
    companion object {
        private const val KEY_LAST_SEEN_VERSION = "last_seen_version"
    }

    /**
     * Checks if the current version is different from the last seen version.
     * If different, updates the stored version and returns true.
     */
    suspend fun checkAndUpdateVersion(currentVersion: String): Boolean {
        val lastSeenVersion = settings.getStringOrNull(KEY_LAST_SEEN_VERSION)
        
        return if (lastSeenVersion != currentVersion) {
            settings.putString(KEY_LAST_SEEN_VERSION, currentVersion)
            true
        } else {
            false
        }
    }

    /**
     * Gets the last seen version
     */
    fun getLastSeenVersion(): String? {
        return settings.getStringOrNull(KEY_LAST_SEEN_VERSION)
    }
}

