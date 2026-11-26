package com.serey.karpiaelearningapi.user.repository

import com.serey.karpiaelearningapi.user.entity.AdminEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AdminRepository: JpaRepository<AdminEntity, Long> {
    fun findByPhoneNumber(phoneNumber: String): Optional<AdminEntity>
    fun findByUser_Id(userId: Long): Optional<AdminEntity>
    fun existsByPhoneNumber(phoneNumber: String): Boolean

}