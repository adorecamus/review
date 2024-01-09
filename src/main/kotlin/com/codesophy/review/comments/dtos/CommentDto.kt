package com.codesophy.review.comments.dtos

import com.codesophy.review.comments.Comment
import java.time.ZonedDateTime

data class CommentDto(
        val id: Long?,
        val username: String,
        val content: String,
        val createdAt: ZonedDateTime
) {
    companion object{
        fun from(comment: Comment): CommentDto{
            return CommentDto(
                    id = comment.id,
                    username = comment.username,
                    content = comment.content,
                    createdAt = comment.createdAt
            )
        }
    }
}
