package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.reviews.dto.CreateReviewRequest
import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.reviews.repository.ReviewRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository):
    ReviewService {
    override fun getAllReviewList(): List<ReviewResponse> {
        return reviewRepository.findAll().map {ReviewResponse.from(it)}
    }

    override fun getReviewById(reviewId: Long): ReviewResponse{
        val review = reviewRepository.findByIdOrNull(reviewId)?:throw Exception("dkald"
        )

        return ReviewResponse.from(review)
    }

    override fun createReview(request: CreateReviewRequest): ReviewResponse {
        val review = reviewRepository.save(
            Review(
                title = request.title,
                content = request.content,
                username = request.username,
                password = request.password
            )
        )
        return ReviewResponse.from(review)
    }
}