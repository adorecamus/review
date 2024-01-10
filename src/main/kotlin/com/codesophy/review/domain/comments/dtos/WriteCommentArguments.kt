package com.codesophy.review.domain.comments.dtos

data class WriteCommentArguments(
        val username: String,
        val password: String,
        val content: String,
)
