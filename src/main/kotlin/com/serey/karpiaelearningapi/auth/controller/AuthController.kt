package com.serey.karpiaelearningapi.auth.controller

import com.serey.karpiaelearningapi.auth.dto.request.StudentRegisterRequest
import com.serey.karpiaelearningapi.auth.dto.response.StudentRegisterResponse
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
    private val studentRegisterService: StudentRegisterService
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
}