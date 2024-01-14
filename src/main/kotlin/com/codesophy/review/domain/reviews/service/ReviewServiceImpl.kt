package com.codesophy.review.domain.reviews.service

import com.codesophy.review.domain.exception.ForbiddenException
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.pagination.CursorResponse
import com.codesophy.review.domain.reviews.dto.*
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.reviews.repository.IReviewRepository
import com.codesophy.review.domain.users.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    private val reviewRepository: IReviewRepository,
    private val userRepository: UserRepository
) : ReviewService {

    override fun getAllReviewList(): List<ReviewDto> {
        return reviewRepository.findAll()
    }

    override fun getReviewById(reviewId: Long): ReviewDto {
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        return ReviewDto.from(review)
    }

    override fun createReview(request: CreateReviewArguments): ReviewDto {

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
        return ReviewDto.from(review)
    }

    override fun updateReview(request: UpdateReviewArguments): ReviewDto {

        val foundReview = request.id?.let {
            reviewRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Review", request.id)

        if (!foundReview.compareUserIdWith(request.userId!!)) {
            throw ForbiddenException(request.userId, "Review", request.id)
        }

        foundReview.changeTitleAndContent(request.title, request.content)
        reviewRepository.save(foundReview)

        return ReviewDto.from(foundReview)
    }

    override fun deleteReview(request: DeleteReviewArguments) {

        val foundReview = request.id?.let {
            reviewRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Review", request.id)

        if (!foundReview.compareUserIdWith(request.userId)) {
            throw ForbiddenException(request.userId, "Review", request.id)
        }

        reviewRepository.deleteById(foundReview.id!!)
    }

    override fun getPaginatedReviewList(
        cursorId: Long?,
        pageSize: Int,
        reviewFeedArguments: ReviewFeedArguments
    ): CursorResponse<ReviewDto> {

        val list = reviewRepository.getPaginatedReviewList(cursorId, pageSize + 1, reviewFeedArguments).toMutableList()
        val hasNext = checkLastPage(pageSize, list.size)
        if (hasNext) {
            list.removeAt(pageSize)
        }
        return CursorResponse(list, hasNext)
    }

    private fun checkLastPage(pageSize: Int, listSize: Int): Boolean {
        return listSize > pageSize
    }

}