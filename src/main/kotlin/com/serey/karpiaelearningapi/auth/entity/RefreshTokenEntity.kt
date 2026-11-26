package com.serey.karpiaelearningapi.auth.entity

import com.serey.karpiaelearningapi.user.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "refresh_tokens")
class RefreshTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @Column(name = "token_hash", nullable = false, unique = true, length = 255)
    var tokenHash: String,

    @Column(name = "expires_at", nullable = false)
    var expiresAt: LocalDateTime,

    @Column(name = "revoked_at")
    var revokedAt: LocalDateTime?  = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
}