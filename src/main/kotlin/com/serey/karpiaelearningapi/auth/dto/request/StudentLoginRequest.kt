package com.serey.karpiaelearningapi.auth.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class StudentLoginRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be format!")
    @field:Size(max = 150, message = "Minimum length is 150")
    val email: String,

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 8,max = 100, message = "Password must be between 8 and 100!")
    val password: String
)
