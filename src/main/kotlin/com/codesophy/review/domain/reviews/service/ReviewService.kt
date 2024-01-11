package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequset

interface ReviewService {

    fun updateReview(reviewId: Long, requset: UpdateReviewRequset): ReviewResponse

    fun deleteReview(reviewId: Long)
}