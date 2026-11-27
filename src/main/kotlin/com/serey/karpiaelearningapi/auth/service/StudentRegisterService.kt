package com.serey.karpiaelearningapi.auth.service

import com.serey.karpiaelearningapi.auth.dto.request.StudentRegisterRequest
import com.serey.karpiaelearningapi.auth.dto.response.StudentRegisterResponse

interface StudentRegisterService {
    fun registerStudent(
        request: StudentRegisterRequest
    ): StudentRegisterResponse
}