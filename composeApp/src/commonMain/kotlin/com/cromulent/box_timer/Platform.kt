package com.cromulent.box_timer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform