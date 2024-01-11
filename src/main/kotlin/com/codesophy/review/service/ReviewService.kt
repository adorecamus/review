package com.codesophy.review.service

import com.codesophy.review.dto.ReviewResponse
import com.codesophy.review.dto.UpdateReviewRequset

interface ReviewService {

    fun updateReview(reviewId: Long, requset: UpdateReviewRequset): ReviewResponse

    fun deleteReview(reviewId: Long)
}