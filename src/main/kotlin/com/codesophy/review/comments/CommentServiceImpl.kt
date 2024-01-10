package com.codesophy.review.comments

import com.codesophy.review.comments.dtos.CommentDto
import com.codesophy.review.comments.dtos.DeleteCommentArguments
import com.codesophy.review.comments.dtos.UpdateCommentArguments
import com.codesophy.review.comments.dtos.WriteCommentArguments
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentRepository: CommentRepository
):CommentService {
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

        foundComment.checkAuthentication(updateCommentArguments.password)
        foundComment.changeContent(updateCommentArguments.content)

        commentRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    override fun deleteComment(deleteCommentArguments: DeleteCommentArguments) {
        val foundComment = deleteCommentArguments.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("target comment is not found")

        foundComment.checkAuthentication(deleteCommentArguments.password)

        commentRepository.deleteById(deleteCommentArguments.id)
    }
}