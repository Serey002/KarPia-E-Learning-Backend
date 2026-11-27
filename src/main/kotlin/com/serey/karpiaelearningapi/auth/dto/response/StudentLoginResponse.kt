package com.serey.karpiaelearningapi.auth.dto.response

data class StudentLoginResponse(
    val userId: Long,
    val studentId: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String? = null,
    val message : String
)