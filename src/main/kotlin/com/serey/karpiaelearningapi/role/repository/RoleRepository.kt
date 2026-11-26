package com.serey.karpiaelearningapi.role.repository

import com.serey.karpiaelearningapi.role.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RoleRepository: JpaRepository<RoleEntity, Long> {
    fun findByName(name: String): Optional<RoleEntity>
    fun existsByName(name: String): Boolean
}