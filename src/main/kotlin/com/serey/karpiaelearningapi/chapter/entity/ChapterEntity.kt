package com.serey.karpiaelearningapi.chapter.entity

import com.serey.karpiaelearningapi.course.entity.CourseEntity
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
@Table(name = "chapters")
class ChapterEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    var course: CourseEntity,

    @Column(name = "title", nullable = false, length = 200)
    var title: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = null,

    @Column(name = "content_url", length = 500)
    var contentUrl: String? = null,

    @Column(name = "chapter_order", nullable = false)
    var chapterOrder: Int,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {

}