package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.ReviewFeedArguments
import com.codesophy.review.domain.reviews.model.Review

interface IReviewRepository {

    fun findAll(): List<ReviewResponse>

    fun findByIdOrIdNull(id: Long): Review?

    fun save(review: Review): Review

    fun deleteById(id: Long)

    fun getPaginatedReviewList(cursorId: Long?, size: Int, reviewFeedArguments: ReviewFeedArguments): List<ReviewResponse>
}