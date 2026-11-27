package com.serey.karpiaelearningapi.auth.service

import com.serey.karpiaelearningapi.auth.dto.request.AdminLoginRequest
import com.serey.karpiaelearningapi.auth.dto.response.AdminLoginResponse

interface AdminLoginService {
    fun adminLogin(
        request: AdminLoginRequest
    ): AdminLoginResponse
}