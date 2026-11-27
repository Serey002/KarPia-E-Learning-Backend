package com.serey.karpiaelearningapi.auth.service.implementation

import com.serey.karpiaelearningapi.auth.dto.request.StudentLoginRequest
import com.serey.karpiaelearningapi.auth.dto.response.StudentLoginResponse
import com.serey.karpiaelearningapi.auth.service.StudentLoginService
import com.serey.karpiaelearningapi.role.repository.RoleRepository
import com.serey.karpiaelearningapi.shared.exception.AuthenticationException
import com.serey.karpiaelearningapi.shared.exception.ForbiddenException
import com.serey.karpiaelearningapi.shared.exception.ResourceNotFoundException
import com.serey.karpiaelearningapi.user.repository.StudentRepository
import com.serey.karpiaelearningapi.user.repository.UserRepository
import com.serey.karpiaelearningapi.user.repository.UserRoleRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentLoginServiceImpl(
    private val userRepository: UserRepository,
    private val studentRepository: StudentRepository,
    private val roleRepository: RoleRepository,
    private val userRoleRepository: UserRoleRepository,
    private val passwordEncoder: PasswordEncoder
): StudentLoginService {

    @Transactional
    override fun studentLogin(
        request: StudentLoginRequest
    ): StudentLoginResponse {
        val email = request.email.trim().lowercase()
        val user = userRepository.findByEmail(email) ?:
        throw AuthenticationException("Email is wrong!")

        if (!user.active) {
            throw AuthenticationException("User is not active!")
        }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw AuthenticationException("Password is wrong!")
        }

        val studentRole = roleRepository.findByName("STUDENT")
            .orElseThrow {
                ResourceNotFoundException("Student role not found!")
            }

        val hasStudentRole = userRoleRepository.existsByUser_IdAndRole_Id(
            userId = requireNotNull(user.id),
            roleId = requireNotNull(studentRole.id)
        )

        if (!hasStudentRole) {
            throw ForbiddenException(
                "You do not have permission to access this resource."
            )
        }

        val student = studentRepository.findByUser_Id(requireNotNull(user.id))
            .orElseThrow {
                ResourceNotFoundException("User not found!")
            }

        return StudentLoginResponse(
            userId = requireNotNull(user.id),
            studentId = requireNotNull(student.id),
            username = user.username,
            email = user.email,
            firstName = student.firstName,
            lastName = student.lastName,
            phoneNumber = student.phoneNumber,
            message = "User login successful."
        )
    }
}