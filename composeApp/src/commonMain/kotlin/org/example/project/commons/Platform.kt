package org.example.project.commons

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform