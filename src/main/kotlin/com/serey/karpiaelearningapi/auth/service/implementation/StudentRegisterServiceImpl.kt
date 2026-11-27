package com.serey.karpiaelearningapi.auth.service.implementation

import com.serey.karpiaelearningapi.auth.dto.request.StudentRegisterRequest
import com.serey.karpiaelearningapi.auth.dto.response.StudentRegisterResponse
import com.serey.karpiaelearningapi.auth.service.StudentRegisterService
import com.serey.karpiaelearningapi.role.repository.RoleRepository
import com.serey.karpiaelearningapi.shared.exception.DuplicateResourceException
import com.serey.karpiaelearningapi.shared.exception.ResourceNotFoundException
import com.serey.karpiaelearningapi.shared.exception.UsernameAlreadyExistException
import com.serey.karpiaelearningapi.user.entity.StudentEntity
import com.serey.karpiaelearningapi.user.entity.UserEntity
import com.serey.karpiaelearningapi.user.entity.UserRoleEntity
import com.serey.karpiaelearningapi.user.repository.StudentRepository
import com.serey.karpiaelearningapi.user.repository.UserRepository
import com.serey.karpiaelearningapi.user.repository.UserRoleRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class StudentRegisterServiceImpl(
    private val userRepository: UserRepository,
    private val studentRepository: StudentRepository,
    private val roleRepository: RoleRepository,
    private val userRoleRepository: UserRoleRepository,
    private val passwordEncoder: PasswordEncoder
): StudentRegisterService {
    @Transactional
    override fun registerStudent(
        request: StudentRegisterRequest
    ): StudentRegisterResponse {
        val username = request.username.trim()
        val email = request.email.trim().lowercase()
        val firstName = request.firstName.trim()
        val lastName = request.lastName.trim()
        val phoneNumber = request.phoneNumber.trim()
        val password = passwordEncoder.encode(request.password)

        if (userRepository.existsByEmail(email)){
            throw DuplicateResourceException(
                "Email already exist!",
            )
        }

        if (userRepository.existsByUsername(username)) {
            throw DuplicateResourceException(
                "Username already exist!",
            )
        }

        if (studentRepository.existsByPhoneNumber(phoneNumber)) {
            throw DuplicateResourceException(
                "Phone number already exist!",
            )
        }

        if (request.password.toByteArray(Charsets.UTF_8).size > 72) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Password must not be more than 72 bytes.",
            )
        }

        val studentRole = roleRepository.findByName("STUDENT")
            .orElseThrow {
                ResourceNotFoundException("Student role not found!", )
            }

        val user = UserEntity(
            username = username,
            email = email,
            password = password
        )
        val savedUser = userRepository.save(user)
        val student = StudentEntity(
            user = savedUser,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
        )

        val savedStudent = studentRepository.save(student)

        val userRole = UserRoleEntity(
            user = savedUser,
            role = studentRole
        )

        userRoleRepository.save(userRole)

        return StudentRegisterResponse(
            userId = requireNotNull(savedUser.id),
            studentId = requireNotNull(savedStudent.id),
            username = savedUser.username,
            email = savedUser.email,
            firstName = savedStudent.firstName,
            lastName = savedStudent.lastName,
            phoneNumber = savedStudent.phoneNumber ?: throw IllegalStateException(
                "Student phone number missing!",
            ),
            message = "Student registered successfully!"
        )
    }
}