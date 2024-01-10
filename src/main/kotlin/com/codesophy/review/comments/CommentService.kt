package com.codesophy.review.comments

import com.codesophy.review.comments.dtos.*

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
}