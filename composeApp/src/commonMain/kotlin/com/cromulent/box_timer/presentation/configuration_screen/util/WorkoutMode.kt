package com.cromulent.box_timer.presentation.configuration_screen.util

enum class WorkoutMode(
    val displayName: String,
    val icon: String,
    val description: String,
    val roundDuration: Long, // in seconds
    val restDuration: Long,  // in seconds
    val rounds: Int
) {
    BOXING("Boxing", "🥊", "Traditional boxing rounds", 180000, 60000, 12),
    HIIT("HIIT", "🔥", "High-Intensity Interval Training", 45000, 15000, 8),
    STRENGTH("Strength", "💪", "Strength training circuits", 120000, 90000, 6),
    TABATA("Tabata", "⚡", "4-minute Tabata protocol", 20000, 10000, 8),
    CARDIO("Cardio", "❤️", "Cardio intervals", 60000, 30000, 10),
    CROSSFIT("CrossFit", "🏋️", "CrossFit WODs", 300000, 120000, 5),
    MMA("MMA", "🥋", "Mixed Martial Arts", 300000, 60000, 3),
    CUSTOM("Custom", "⚙️", "Create your own workout", 60000, 30000, 5);
}