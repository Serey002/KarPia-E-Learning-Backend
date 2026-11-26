package com.serey.karpiaelearningapi.permission.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class RolePermissionId(
    @Column(name = "role_id")
    var roleId: Long? = null,
    @Column(name = "permission_id")
    var permissionId: String? = null,
): Serializable
