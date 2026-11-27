package com.serey.karpiaelearningapi.auth.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class StudentRegisterRequest(
    @field:NotBlank(message = "Username is required!")
    @field:Size(min = 2, max = 100, message = "Username must be between 2 and 100!")
    val username: String,

    @field:NotBlank(message = "Email is required!")
    @field:Email(message = "Email must be format!")
    @field:Size(max = 150, message = "Email must be 150 characters!")
    val email: String,

    @field:NotBlank(message = "Password is required!")
    @field:Size(min = 8, max = 100, message = "Password must be between 8 and 100!")
    val password: String,

    @field:NotBlank(message = "First name is required!")
    @field:Size( min = 2,max = 100, message = "First name must be between 2 and 100!")
    val firstName: String,

    @field:NotBlank(message = "Last name is required!")
    @field:Size( min = 2,max = 100, message = "Last name must be between 2 and 100!")
    val lastName: String,

    @field:NotBlank(message = "Phone number is required!")
    @field:Size(min = 8, max = 100, message = "Phone number must be between 8 and 100!")
    val phoneNumber: String
)