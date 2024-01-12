package com.codesophy.review.domain.comments

import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.comments.repository.CommentJpaRepository
import com.codesophy.review.domain.comments.repository.ICommentRepository
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.pagination.PageResponse
import com.codesophy.review.domain.reviews.repository.ReviewJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentRepository: ICommentRepository,
        private val commentJpaRepository: CommentJpaRepository,
        private val reviewJpaRepository: ReviewJpaRepository,
): CommentService {
    override fun writeComment(
            reviewId: Long,
            writeCommentArguments: WriteCommentArguments
    ): CommentDto {
        val review = reviewJpaRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val comment = Comment(
                username = writeCommentArguments.username,
                content = writeCommentArguments.content,
                review = review
        )
        val result = commentRepository.save(comment)
        return CommentDto.from(result)
    }

    override fun updateComment(
            reviewId: Long,
            updateCommentArguments: UpdateCommentArguments
    ): CommentDto {
        reviewJpaRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val foundComment = updateCommentArguments.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Comment", updateCommentArguments.id)

        foundComment.changeContent(updateCommentArguments.content)

        commentRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    override fun deleteComment(reviewId: Long, commentId: Long) {
        commentJpaRepository.findByReviewIdAndId(reviewId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        commentRepository.deleteById(commentId)
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