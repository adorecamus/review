package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.Comment
import com.codesophy.review.infra.querydsl.QueryDslSupport
import org.springframework.data.repository.findByIdOrNull

class CommentRepositoryImpl(
    private val commentJpaRepository: CommentJpaRepository
): ICommentRepository, QueryDslSupport() {
    override fun save(comment: Comment): Comment {
        return commentJpaRepository.save(comment)
    }

    override fun findByIdOrNull(id: Long): Comment? {
        return commentJpaRepository.findByIdOrNull(id)
    }

    override fun deleteById(id: Long) {
        return commentJpaRepository.deleteById(id)
    }
}