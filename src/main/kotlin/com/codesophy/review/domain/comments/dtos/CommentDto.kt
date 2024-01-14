package com.codesophy.review.domain.comments.dtos

import com.codesophy.review.domain.comments.model.Comment
import java.time.ZonedDateTime

data class CommentDto(
        var id: Long?,
        val nickname: String,
        val content: String,
        val createdAt: ZonedDateTime
) {
    companion object{
        fun from(comment: Comment): CommentDto {
            return CommentDto(
                    id = comment.id,
                    nickname = comment.user.nickname,
                    content = comment.content,
                    createdAt = comment.createdAt
            )
        }
    }
}
