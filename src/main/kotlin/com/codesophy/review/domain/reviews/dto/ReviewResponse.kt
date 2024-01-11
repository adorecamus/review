package com.codesophy.review.domain.reviews.dto

import com.codesophy.review.domain.reviews.model.Review
import java.time.ZonedDateTime

data class ReviewResponse(
    val id: Long?,
    val title: String,
    val content: String,
    val username: String,
    val createdAt: ZonedDateTime = ZonedDateTime.now()
) {
    companion object{
        fun from(review: Review): ReviewResponse {
                return ReviewResponse(
                    id = review.id,
                    title = review.title,
                    content = review.content,
                    username = review.username,
                    createdAt = review.createdAt
                )
        }
    }
}

