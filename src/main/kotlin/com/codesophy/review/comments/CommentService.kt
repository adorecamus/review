package com.codesophy.review.comments

import com.codesophy.review.comments.dtos.CommentDto
import com.codesophy.review.comments.dtos.DeleteCommentArguments
import com.codesophy.review.comments.dtos.UpdateCommentArguments
import com.codesophy.review.comments.dtos.WriteCommentArguments

interface CommentService {
    fun writeComment(
            writeCommentArguments: WriteCommentArguments
    ): CommentDto
    fun updateComment(
            updateCommentArguments: UpdateCommentArguments
    ): CommentDto
    fun deleteComment(
            deleteCommentArguments: DeleteCommentArguments
    )
}