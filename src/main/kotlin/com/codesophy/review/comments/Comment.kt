package com.codesophy.review.comments

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "comments")
class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        val content: String,
        @Column
        val username: String,
        @Column
        val password: String,
){
        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now()
}