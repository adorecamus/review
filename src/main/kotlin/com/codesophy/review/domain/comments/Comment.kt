package com.codesophy.review.domain.comments

import com.codesophy.review.domain.reviews.model.Review
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "comment")
class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var content: String,
        @Column
        val username: String,
        @ManyToOne
        var review : Review
){

        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now()

        fun changeContent(content: String){
                this.content = content
        }
}