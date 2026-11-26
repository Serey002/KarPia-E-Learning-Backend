package com.serey.karpiaelearningapi.user.repository

import com.serey.karpiaelearningapi.user.entity.UserRoleEntity
import com.serey.karpiaelearningapi.user.entity.UserRoleId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository: JpaRepository<UserRoleEntity, UserRoleId> {
    fun findAllByUser_Id(userId: Long): List<UserRoleEntity>
    fun findAllByRole_Id(roleId: Long): List<UserRoleEntity>
    fun existsByUser_IdAndRole_Id(userId: Long, email: String): Boolean
}