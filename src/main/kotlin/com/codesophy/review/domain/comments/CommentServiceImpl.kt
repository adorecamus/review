package com.codesophy.review.domain.comments

import com.codesophy.review.domain.comments.dtos.CheckPasswordArguments
import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.comments.repository.ICommentRepository
import com.codesophy.review.domain.pagination.PageResponse
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentRepository: ICommentRepository
): CommentService {
    override fun writeComment(
            writeCommentArguments: WriteCommentArguments
    ): CommentDto {
        val comment = Comment(
                username = writeCommentArguments.username,
                password = writeCommentArguments.password,
                content = writeCommentArguments.content,
        )
        val result = commentRepository.save(comment)
        return CommentDto.from(result)
    }

    override fun updateComment(
            updateCommentArguments: UpdateCommentArguments
    ): CommentDto {
        val foundComment = updateCommentArguments.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target comment is not found")

        foundComment.changeContent(updateCommentArguments.content)

        commentRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    override fun deleteComment(commentId: Long) {
        val foundComment = commentRepository.findByIdOrNull(commentId)
            ?: throw Exception("target comment is not found")

        commentRepository.deleteById(commentId)
    }

    override fun checkPassword(checkPasswordArguments: CheckPasswordArguments) {
        val foundComment = checkPasswordArguments.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target comment is not found")

        foundComment.checkAuthentication(checkPasswordArguments.password)
    }

    override fun getPaginatedCommentList(pageNumber: Int, pageSize: Int): PageResponse<CommentDto> {
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