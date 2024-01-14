package com.codesophy.review.domain.comments.model

import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.users.model.User
import jakarta.persistence.*
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
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,
        @ManyToOne
        var review : Review
){

        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now()

        fun changeContent(content: String){
                this.content = content
        }
        fun compareUserIdWith(userId: Long): Boolean {
                return user.id == userId
        }
}