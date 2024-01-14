package com.codesophy.review.domain.reviews.model

import com.codesophy.review.domain.comments.model.Comment
import com.codesophy.review.domain.users.model.User
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(mappedBy = "review", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments : List<Comment> = emptyList(),

    @CreationTimestamp
    @Column
    val createdAt: ZonedDateTime = ZonedDateTime.now()
) {

    fun changeTitleAndContent(title: String, content: String) {
        this.title = title
        this.content = content
    }

    fun compareUserIdWith(userId: Long): Boolean {
        return user.id == userId
    }
}
