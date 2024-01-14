package com.codesophy.review.domain.reviews.dto

data class CreateReviewArguments(
    val title: String,
    val content: String,
    val userId: Long?,
)
