package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.pagination.CursorResponse
import com.codesophy.review.domain.reviews.dto.*

interface ReviewService {
    fun getAllReviewList(): List<ReviewResponse>

    fun getReviewById(reviewId: Long): ReviewResponse

    fun createReview(request: CreateReviewRequest): ReviewResponse

    fun updateReview(request: UpdateReviewRequest): ReviewResponse

    fun deleteReview(request: DeleteReviewRequest)

    fun getPaginatedReviewList(
        cursorId: Long?,
        pageSize: Int,
        reviewFeedArguments: ReviewFeedArguments
    ): CursorResponse<ReviewResponse>

}