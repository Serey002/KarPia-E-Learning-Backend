package com.serey.karpiaelearningapi.auth.service.implementation

import com.serey.karpiaelearningapi.auth.dto.request.AdminLoginRequest
import com.serey.karpiaelearningapi.auth.dto.response.AdminLoginResponse
import com.serey.karpiaelearningapi.auth.service.AdminLoginService
import com.serey.karpiaelearningapi.role.repository.RoleRepository
import com.serey.karpiaelearningapi.shared.exception.AuthenticationException
import com.serey.karpiaelearningapi.shared.exception.ForbiddenException
import com.serey.karpiaelearningapi.shared.exception.ResourceNotFoundException
import com.serey.karpiaelearningapi.user.repository.AdminRepository
import com.serey.karpiaelearningapi.user.repository.UserRepository
import com.serey.karpiaelearningapi.user.repository.UserRoleRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLoginServiceImpl(
    private val userRepository: UserRepository,
    private val adminRepository: AdminRepository,
    private val roleRepository: RoleRepository,
    private val userRoleRepository: UserRoleRepository,
    private val passwordEncoder: PasswordEncoder
): AdminLoginService {

    @Transactional
    override fun adminLogin(
        request: AdminLoginRequest
    ): AdminLoginResponse {
        val email = request.email.trim().lowercase()
        val user = userRepository.findByEmail(email) ?:
        throw AuthenticationException(
            "Email is wrong!"
        )

        if (!user.active) {
            throw ForbiddenException(
                "Account is not active!"
            )
        }

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw AuthenticationException(
                "Password is wrong!"
            )
        }

        val adminRole = roleRepository.findByName("ADMIN")
            .orElseThrow {
                ResourceNotFoundException("ADMIN role not found!")
            }

        val hasAdminRole = userRoleRepository.existsByUser_IdAndRole_Id(
            userId = requireNotNull(user.id),
            roleId = requireNotNull(adminRole.id)
        )
        if (!hasAdminRole) {
            throw ForbiddenException(
                "You do not have permission to access this resource."
            )
        }

        val admin = adminRepository.findByUser_Id(requireNotNull(user.id))
            .orElseThrow {
                ResourceNotFoundException(
                    "User not found."
                )
            }

        return AdminLoginResponse(
            userId = requireNotNull(user.id),
            adminId = requireNotNull(admin.id),
            username = user.username,
            email = user.email,
            firstName = admin.firstName,
            lastName = admin.lastName,
            phoneNumber = admin.phoneNumber,
            message = "Admin login successfully!"
        )
    }
}