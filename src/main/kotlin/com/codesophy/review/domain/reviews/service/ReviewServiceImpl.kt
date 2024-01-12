package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.pagination.CursorResponse
import com.codesophy.review.domain.reviews.dto.CreateReviewRequest
import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequest
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.reviews.repository.IReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    val reviewRepository: IReviewRepository
) : ReviewService {

    override fun getAllReviewList(): List<ReviewResponse> {
        return reviewRepository.findAll().map { ReviewResponse.from(it) }
    }

    override fun getReviewById(reviewId: Long): ReviewResponse {
        val review = reviewRepository.findByIdOrIdNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

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

    override fun updateReview(reviewId: Long, request: UpdateReviewRequest): ReviewResponse {
        val savedReview = reviewRepository.save(request.to())

        return ReviewResponse.from(savedReview)
    }

    override fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }

    override fun getPaginatedReviewList(cursorId: Long?, pageSize: Int): CursorResponse<ReviewResponse> {
        val list = reviewRepository.getLimitedReviewsLessThanId(cursorId, pageSize + 1).toMutableList()
        val hasNext = checkLastPage(pageSize, list)
        if (hasNext) {
            list.removeAt(pageSize)
        }
        return CursorResponse(list.map { ReviewResponse.from(it) }, hasNext)
    }

    private fun checkLastPage(pageSize: Int, list: MutableList<Review>): Boolean {
        return list.size > pageSize
    }

}