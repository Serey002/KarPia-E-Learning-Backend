package com.serey.karpiaelearningapi.auth.dto.response

data class AdminLoginResponse(
    val userId: Long,
    val adminId: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String? = null,
    val message : String
)
