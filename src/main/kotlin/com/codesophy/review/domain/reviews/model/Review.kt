package com.codesophy.review.domain.reviews.model

import com.codesophy.review.domain.reviews.dto.ReviewResponse
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "review")
class Review(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content",nullable = false)
    var content: String,

    @Column(name = "username",nullable = false)
    var username: String,

    @Column
    val password: String,

    @CreationTimestamp
    @Column
    val createdAt: ZonedDateTime = ZonedDateTime.now()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
