package com.codesophy.review.comments

import com.codesophy.review.comments.dtos.CommentDto
import com.codesophy.review.comments.dtos.WriteCommentArguments
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentRepository: CommentRepository
):CommentService
{
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

}