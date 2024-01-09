package com.codesophy.review.comments

import com.codesophy.review.comments.dtos.CommentDto
import com.codesophy.review.comments.dtos.WriteCommentArguments

interface CommentService {
    fun writeComment(
            writeCommentArguments: WriteCommentArguments
    ): CommentDto
}