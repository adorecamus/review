package com.codesophy.review.domain.comments


import com.codesophy.review.domain.comments.dtos.CheckPasswordArguments
import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.pagination.PageResponse

interface CommentService {
    fun writeComment(
            writeCommentArguments: WriteCommentArguments
    ): CommentDto
    fun updateComment(
            updateCommentArguments: UpdateCommentArguments
    ): CommentDto
    fun deleteComment(
            commentId: Long
    )
    fun checkPassword(
            checkPasswordArguments: CheckPasswordArguments
    )
    fun getPaginatedCommentList(
            pageNumber: Int, pageSize: Int
    ): PageResponse<CommentDto>
}