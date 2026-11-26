package com.serey.karpiaelearningapi.user.repository

import com.serey.karpiaelearningapi.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findByUsername(username: String): Optional<UserEntity>
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean
}