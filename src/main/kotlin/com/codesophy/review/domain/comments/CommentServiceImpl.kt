package com.codesophy.review.domain.comments

import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.DeleteCommentArgument
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.comments.repository.CommentJpaRepository
import com.codesophy.review.domain.comments.repository.ICommentRepository
import com.codesophy.review.domain.exception.ForbiddenException
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.pagination.PageResponse
import com.codesophy.review.domain.reviews.repository.ReviewJpaRepository
import com.codesophy.review.domain.users.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentRepository: ICommentRepository,
        private val reviewJpaRepository: ReviewJpaRepository,
        private val userRepository: UserRepository
): CommentService {
    override fun writeComment(
            reviewId: Long,
            request: WriteCommentArguments
    ): CommentDto {
        val user = request.userId?.let {
            userRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("UserId", request.userId)

        val review = reviewJpaRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val comment = Comment(
                content = request.content,
                user = user,
                review = review
        )
        val result = commentRepository.save(comment)
        return CommentDto.from(result)
    }

    override fun updateComment(
            reviewId: Long,
            request: UpdateCommentArguments
    ): CommentDto {
        reviewJpaRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val foundComment = request.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Comment", request.id)

        if(!foundComment.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId, "UserId", request.id)
        }
        foundComment.changeContent(request.content)

        commentRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    override fun deleteComment(request: DeleteCommentArgument) {
        val foundComment = request.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Comment", request.id)

        if(!foundComment.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId, "UserId", request.id)
        }

        commentRepository.deleteById(foundComment.id!!)
    }

    override fun getPaginatedCommentList(reviewId: Long, pageNumber: Int, pageSize: Int): PageResponse<CommentDto> {
        reviewJpaRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        validatePageRequest(pageNumber, pageSize)

        val totalPages = commentRepository.getTotalPages(pageSize)
        if (pageNumber > totalPages) {
            throw IllegalArgumentException("Page number must not be greater than total pages")
        }

        return PageResponse(
                dtoList = commentRepository.getListByPageNumberAndPageSize(pageNumber, pageSize)
                            .map { CommentDto.from(it) },
                totalPages = totalPages
        )
    }

    private fun validatePageRequest(pageNumber: Int, pageSize: Int) {
        if (pageNumber < 1) {
            throw IllegalArgumentException("Page number must not be less than one")
        }
        if (pageSize < 1) {
            throw IllegalArgumentException("Page size must not be less than one")
        }
    }

}