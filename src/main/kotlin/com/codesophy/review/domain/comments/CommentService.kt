package com.codesophy.review.domain.comments


import com.codesophy.review.domain.comments.dtos.CheckPasswordArguments
import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.pagination.PageResponse

interface CommentService {
    fun writeComment(
            reviewId: Long,
            writeCommentArguments: WriteCommentArguments
    ): CommentDto
    fun updateComment(
            reviewId: Long,
            updateCommentArguments: UpdateCommentArguments
    ): CommentDto
    fun deleteComment(
            reviewId: Long,
            commentId: Long
    )
    fun checkPassword(
            reviewId: Long,
            checkPasswordArguments: CheckPasswordArguments
    )
    fun getPaginatedCommentList(
            reviewId: Long,
            pageNumber: Int, pageSize: Int
    ): PageResponse<CommentDto>
}