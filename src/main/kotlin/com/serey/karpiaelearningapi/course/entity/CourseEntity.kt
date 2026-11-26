package com.serey.karpiaelearningapi.course.entity

import com.serey.karpiaelearningapi.category.entity.CategoryEntity
import com.serey.karpiaelearningapi.user.entity.AdminEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "courses")
class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    var category: CategoryEntity,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_admin_id", nullable = false)
    var createdByAdmin: AdminEntity,

    @Column(name = "title", nullable = false, length = 200)
    var title: String,

    @Column(name = "slug", nullable = false, unique = true, length = 220)
    var slug: String,

    @Column(name = "thumbnail_url", length = 500)
    var thumbnailUrl: String? = null,

    @Column(name = "status", nullable = false, length = 30)
    var status: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @PreUpdate
    fun update() {
        updatedAt = LocalDateTime.now()
    }
}