package com.serey.karpiaelearningapi.user.entity

import com.serey.karpiaelearningapi.role.entity.RoleEntity
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_roles")
class UserRoleEntity(
    @EmbeddedId
    var id: UserRoleId = UserRoleId(),

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var role: RoleEntity,

    @Column(name = "assigned_at", nullable = false)
    val assignedAt: LocalDateTime = LocalDateTime.now(),
) {
}