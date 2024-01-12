package com.codesophy.review.domain.reviews.model

import jakarta.persistence.*
import java.time.ZonedDateTime
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "review")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "username")
    var username: String,

    @Column
    val password: String,

    @CreationTimestamp
    @Column
    val createdAt: ZonedDateTime = ZonedDateTime.now()
)
