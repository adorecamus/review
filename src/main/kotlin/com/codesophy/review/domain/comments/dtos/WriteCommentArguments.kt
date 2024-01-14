package com.codesophy.review.domain.comments.dtos

data class WriteCommentArguments(
        val content: String,
        val userId: Long?
)
