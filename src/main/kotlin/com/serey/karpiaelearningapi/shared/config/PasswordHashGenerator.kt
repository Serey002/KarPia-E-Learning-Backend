package com.serey.karpiaelearningapi.shared.config

import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordHashGenerator(
    private val passwordEncoder: PasswordEncoder
): CommandLineRunner {
    override fun run(vararg args: String) {
        val rawPassword = "admin"
        val hashedPassword = passwordEncoder.encode(rawPassword)

//        println("Hashed Password: $hashedPassword")
    }
}