package com.serey.karpiaelearningapi.user.repository

import com.serey.karpiaelearningapi.user.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface StudentRepository: JpaRepository<StudentEntity, Long> {
    fun findByPhoneNumber(phoneNumber: String): Optional<StudentEntity>
    fun findByUser_Id(userId: Long): Optional<StudentEntity>
    fun existsByPhoneNumber(phoneNumber: String): Boolean
}