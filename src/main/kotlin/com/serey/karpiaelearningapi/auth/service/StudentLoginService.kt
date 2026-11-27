package com.serey.karpiaelearningapi.auth.service

import com.serey.karpiaelearningapi.auth.dto.request.StudentLoginRequest
import com.serey.karpiaelearningapi.auth.dto.response.StudentLoginResponse

interface StudentLoginService {
    fun studentLogin(
        request: StudentLoginRequest
    ): StudentLoginResponse
}