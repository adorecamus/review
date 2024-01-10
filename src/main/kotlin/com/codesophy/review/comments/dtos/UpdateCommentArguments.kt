package com.codesophy.review.comments.dtos

data class UpdateCommentArguments(
        val id: Long?,
        val content: String,
        val password: String
)
