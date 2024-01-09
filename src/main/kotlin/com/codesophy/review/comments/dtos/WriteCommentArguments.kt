package com.codesophy.review.comments.dtos

data class WriteCommentArguments(
        val username: String,
        val password: String,
        val content: String,
)
