package com.serey.karpiaelearningapi.permission.repository

import com.serey.karpiaelearningapi.permission.entity.RolePermissionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RolePermissionRepository: JpaRepository<RolePermissionEntity, Long> {
    fun findAllByRole_Id(roleId: Long): List<RolePermissionEntity>
    fun findAllByPermission_Id(permissionId: Long): List<RolePermissionEntity>
    fun existsByRole_idAndPermission_id(roleId: Long, permissionId: Long): Boolean
}