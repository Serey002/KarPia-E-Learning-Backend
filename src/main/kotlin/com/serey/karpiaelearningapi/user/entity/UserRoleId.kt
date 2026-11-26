package com.serey.karpiaelearningapi.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class UserRoleId(
    @Column(name = "user_id")
    var userId: Long? = null,

    @Column(name = "role_id")
    var roleId: Long? = null
): Serializable
