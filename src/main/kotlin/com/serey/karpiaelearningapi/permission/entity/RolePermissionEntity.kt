package com.serey.karpiaelearningapi.permission.entity

import com.serey.karpiaelearningapi.role.entity.RoleEntity
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table


@Entity
@Table(name = "role_permission")
class RolePermissionEntity(
    @EmbeddedId
    var id: RolePermissionId = RolePermissionId(),

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    var role: RoleEntity,

    @MapsId("permissionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false)
    var permission: PermissionEntity
)