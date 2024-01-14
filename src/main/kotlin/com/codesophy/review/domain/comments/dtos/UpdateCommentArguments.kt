package com.codesophy.review.domain.comments.dtos

data class UpdateCommentArguments(
        val id: Long?,
        val content: String,
        val userId: Long?
)
