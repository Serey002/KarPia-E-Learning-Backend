package com.serey.karpiaelearningapi.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "students")
class StudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
    )
    var user: UserEntity,
    @Column(name = "first_name", nullable = false, length = 100)
    var firstName: String,
    @Column(name = "last_name", nullable = false, length = 100)
    var lastName: String,
    @Column(name = "phone_number", unique = true, length = 30)
    var phoneNumber: String? = null,
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}