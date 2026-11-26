package com.serey.karpiaelearningapi.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false, length = 100)
    var username: String,
    @Column(nullable = false, unique = true, length = 150)
    var email: String,
    @Column(nullable = false, length = 255)
    var password: String,
    @Column(name = "is_active",nullable = false)
    var active: Boolean,
    @Column(name = "created_at",nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column( name = "updated_at",nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    @PreUpdate
    fun updateTimestamp() {
        updatedAt = LocalDateTime.now()
    }
}