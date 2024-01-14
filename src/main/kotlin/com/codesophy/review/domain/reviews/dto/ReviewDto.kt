package com.codesophy.review.domain.reviews.dto

import com.codesophy.review.domain.reviews.model.Review
import java.time.ZonedDateTime

data class ReviewDto(
    val id: Long?,
    val title: String,
    val content: String,
    val nickname: String,
    val createdAt: ZonedDateTime
) {
    companion object{
        fun from(review: Review): ReviewDto {
            return ReviewDto(
                id = review.id,
                title = review.title,
                content = review.content,
                nickname = review.user.nickname,
                createdAt = review.createdAt
            )
        }
    }
}
