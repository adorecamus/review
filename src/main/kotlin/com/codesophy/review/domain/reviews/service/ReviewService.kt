package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.pagination.CursorResponse
import com.codesophy.review.domain.reviews.dto.*

interface ReviewService {
    fun getAllReviewList(): List<ReviewDto>

    fun getReviewById(reviewId: Long): ReviewDto

    fun createReview(request: CreateReviewArguments): ReviewDto

    fun updateReview(request: UpdateReviewArguments): ReviewDto

    fun deleteReview(request: DeleteReviewArguments)

    fun getPaginatedReviewList(
        cursorId: Long?,
        pageSize: Int,
        reviewFeedArguments: ReviewFeedArguments
    ): CursorResponse<ReviewDto>

}