package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.exception.ForbiddenException
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.pagination.CursorResponse
import com.codesophy.review.domain.reviews.dto.CreateReviewRequest
import com.codesophy.review.domain.reviews.dto.DeleteReviewRequest
import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequest
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.reviews.repository.IReviewRepository
import com.codesophy.review.domain.users.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    private val reviewRepository: IReviewRepository,
    private val userRepository: UserRepository
) : ReviewService {

    override fun getAllReviewList(): List<ReviewResponse> {
        return reviewRepository.findAll().map { ReviewResponse.from(it) }
    }

    override fun getReviewById(reviewId: Long): ReviewResponse {
        val review = reviewRepository.findByIdOrIdNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        return ReviewResponse.from(review)
    }

    override fun createReview(request: CreateReviewRequest): ReviewResponse {

        val user = request.userId?.let {
            userRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("User", request.userId)

        val review = reviewRepository.save(
            Review(
                title = request.title,
                content = request.content,
                user = user
            )
        )
        return ReviewResponse.from(review)
    }

    override fun updateReview(request: UpdateReviewRequest): ReviewResponse {

        val foundReview = request.id?.let {
            reviewRepository.findByIdOrIdNull(it)
        } ?: throw ModelNotFoundException("Review", request.id)

        if (!foundReview.compareUserIdWith(request.userId!!)) {
            throw ForbiddenException(request.userId, "Review", request.id)
        }

        foundReview.changeTitleAndContent(request.title, request.content)
        reviewRepository.save(foundReview)

        return ReviewResponse.from(foundReview)
    }

    override fun deleteReview(request: DeleteReviewRequest) {

        val foundReview = request.id?.let {
            reviewRepository.findByIdOrIdNull(it)
        } ?: throw ModelNotFoundException("Review", request.id)

        if (!foundReview.compareUserIdWith(request.userId)) {
            throw ForbiddenException(request.userId, "Review", request.id)
        }

        reviewRepository.deleteById(foundReview.id!!)
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