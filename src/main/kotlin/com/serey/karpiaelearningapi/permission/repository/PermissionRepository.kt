package com.serey.karpiaelearningapi.permission.repository

import com.serey.karpiaelearningapi.permission.entity.PermissionEntity
import com.serey.karpiaelearningapi.role.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PermissionRepository: JpaRepository<PermissionEntity, Long> {

    fun findByName(name: String): Optional<PermissionEntity>
    fun existsByName(name: String): Boolean
}