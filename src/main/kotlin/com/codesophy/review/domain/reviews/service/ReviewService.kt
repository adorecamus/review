package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.reviews.dto.CreateReviewRequest
import com.codesophy.review.domain.reviews.dto.ReviewResponse

interface ReviewService {
    fun getAllReviewList(): List<ReviewResponse>

    fun getReviewById(reviewId: Long): ReviewResponse

    fun createReview(request: CreateReviewRequest): ReviewResponse
}