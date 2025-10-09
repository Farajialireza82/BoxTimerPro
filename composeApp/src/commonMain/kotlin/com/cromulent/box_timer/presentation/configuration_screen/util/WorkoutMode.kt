package com.cromulent.box_timer.presentation.configuration_screen.util

enum class WorkoutMode(
    val displayName: String,
    val icon: String,
    val description: String,
    val roundDuration: Long, // in seconds
    val restDuration: Long,  // in seconds
    val rounds: Int
) {
    MMA("MMA", "🦅", "Mixed Martial Arts", 300000, 60000, 3),
    BOXING("Boxing", "🥊", "Traditional boxing rounds", 180000, 60000, 12),
    STRENGTH("Strength", "💪", "Strength training circuits", 120000, 90000, 6),
    CARDIO("Cardio", "\uD83C\uDFC3", "Cardio intervals", 60000, 30000, 10),
    HIIT("HIIT", "🔥", "High-Intensity Interval Training", 45000, 15000, 8),
    TABATA("Tabata", "⚡", "4-minute Tabata protocol", 20000, 10000, 8),
    CUSTOM("Custom", "⚙️", "Create your own workout", 60000, 30000, 5);
}