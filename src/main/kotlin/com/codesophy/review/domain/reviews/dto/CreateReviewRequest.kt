package com.codesophy.review.domain.reviews.dto

data class CreateReviewRequest(
    val title: String,
    val content: String,
    val username: String,
    val password: String
)