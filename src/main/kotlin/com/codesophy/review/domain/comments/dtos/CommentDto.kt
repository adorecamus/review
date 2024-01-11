package com.codesophy.review.domain.comments.dtos

import com.codesophy.review.domain.comments.Comment
import java.time.ZonedDateTime

data class CommentDto(
        var id: Long?,
        val username: String,
        val content: String,
        val createdAt: ZonedDateTime
) {
    companion object{
        fun from(comment: Comment): CommentDto {
            return CommentDto(
                    id = comment.id,
                    username = comment.username,
                    content = comment.content,
                    createdAt = comment.createdAt
            )
        }
    }
}
