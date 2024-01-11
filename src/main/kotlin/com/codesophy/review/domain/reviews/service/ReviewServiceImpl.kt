package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequset
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.reviews.repository.ReviewRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.contracts.contract

//@Service
//class ReviewServiceImpl(
//    private val reviewRepository: ReviewRepository
//): ReviewService {
//    @Transactional
//    override fun updateCourse(reviewId: Long, request: UpdateReviewRequset): ReviewResponse {
//        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
//        val (title, content) = request
//
//        review.title = title
//        review.content = content
//
//        return reviewRepository.save(review).toResponse()
//    }
//
//
//    @Transactional
//    override fun deleteReview(reviewId: Long) {
//        TODO("Not yet implemented")
//    }
//}

@Service
class ReviewServiceImpl(
    val reviewRepository: ReviewRepository
): ReviewService {
    override fun updateReview(reviewId: Long, reviewRequset: UpdateReviewRequset): ReviewResponse {
        val savedReview = reviewRepository.save(reviewRequset.to())

        return ReviewResponse.from(savedReview)
    }

    override fun deleteReview(reviewId: Long) {
        TODO("Not yet implemented")
    }


}