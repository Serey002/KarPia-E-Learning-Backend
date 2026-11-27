package com.serey.karpiaelearningapi.auth.controller

import com.serey.karpiaelearningapi.auth.dto.request.AdminLoginRequest
import com.serey.karpiaelearningapi.auth.dto.request.StudentLoginRequest
import com.serey.karpiaelearningapi.auth.dto.request.StudentRegisterRequest
import com.serey.karpiaelearningapi.auth.dto.response.AdminLoginResponse
import com.serey.karpiaelearningapi.auth.dto.response.StudentLoginResponse
import com.serey.karpiaelearningapi.auth.dto.response.StudentRegisterResponse
import com.serey.karpiaelearningapi.auth.service.AdminLoginService
import com.serey.karpiaelearningapi.auth.service.StudentLoginService
import com.serey.karpiaelearningapi.auth.service.StudentRegisterService
import com.serey.karpiaelearningapi.auth.service.implementation.StudentRegisterServiceImpl
import com.serey.karpiaelearningapi.shared.Appconstants
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Appconstants.MAIN_PATH+"/auth")
class AuthController(
    private val studentRegisterService: StudentRegisterService,
    private val adminLoginService: AdminLoginService,
    private val studentLoginService: StudentLoginService
) {

    @PostMapping("/register/student")
    fun registerStudent(
        @Valid @RequestBody request: StudentRegisterRequest
    ): ResponseEntity<StudentRegisterResponse> {
        val response = studentRegisterService.registerStudent(request)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                response
            )
    }

    @PostMapping("/login/student")
    fun loginStudent(
        @Valid @RequestBody request: StudentLoginRequest
    ): ResponseEntity<StudentLoginResponse> {
        val response = studentLoginService.studentLogin(request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                response
            )
    }

    @PostMapping("/login/admin")
    fun loginAdmin(
        @Valid @RequestBody request: AdminLoginRequest
    ): ResponseEntity<AdminLoginResponse> {
        val response = adminLoginService.adminLogin(request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                response
            )
    }
}