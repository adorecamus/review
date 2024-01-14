package com.codesophy.review.domain.comments.service


import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.DeleteCommentArgument
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.pagination.PageResponse

interface CommentService {
    fun writeComment(
            reviewId: Long,
            request: WriteCommentArguments
    ): CommentDto
    fun updateComment(
            reviewId: Long,
            request: UpdateCommentArguments
    ): CommentDto
    fun deleteComment(
            request: DeleteCommentArgument
    )
    fun getPaginatedCommentList(
            reviewId: Long,
            pageNumber: Int, pageSize: Int
    ): PageResponse<CommentDto>
}