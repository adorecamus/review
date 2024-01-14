package com.codesophy.review.domain.reviews.dto

data class UpdateReviewArguments(
    val id: Long?,
    val title:String,
    val content: String,
    val userId: Long?,
)
