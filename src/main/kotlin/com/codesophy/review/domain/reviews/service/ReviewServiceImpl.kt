package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequset
import com.codesophy.review.domain.reviews.repository.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    val reviewRepository: ReviewRepository
): ReviewService {
    override fun updateReview(reviewId: Long, reviewRequset: UpdateReviewRequset): ReviewResponse {
        val savedReview = reviewRepository.save(reviewRequset.to())

        return ReviewResponse.from(savedReview)
    }

    override fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }
}