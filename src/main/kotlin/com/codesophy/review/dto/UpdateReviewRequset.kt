package com.codesophy.review.dto

import com.codesophy.review.model.Review

data class UpdateReviewRequset(
    val id: Long?,
    val title:String,
    val content: String,
    val username: String
) {
    fun to(): Review {
        return Review(
            id = id,
            title = title,
            content = content,
            username = username
        )
    }
}
