package com.codesophy.review.domain.reviews.dto

import com.codesophy.review.domain.reviews.model.Review

data class UpdateReviewRequest(
    val id: Long?,
    val title:String,
    val content: String,
    val username: String,
) {
    fun to(): Review {
        return Review(
            id = id,
            title = title,
            content = content,
            username = username,
        )
    }
}
