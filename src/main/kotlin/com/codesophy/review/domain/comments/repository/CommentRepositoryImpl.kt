package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.Comment
import com.codesophy.review.domain.comments.QComment
import com.codesophy.review.infra.querydsl.QueryDslSupport
import org.springframework.data.repository.findByIdOrNull
import kotlin.math.ceil

class CommentRepositoryImpl(
    private val commentJpaRepository: CommentJpaRepository
): ICommentRepository, QueryDslSupport() {

    private val comment = QComment.comment

    override fun save(comment: Comment): Comment {
        return commentJpaRepository.save(comment)
    }

    override fun findByIdOrNull(id: Long): Comment? {
        return commentJpaRepository.findByIdOrNull(id)
    }

    override fun deleteById(id: Long) {
        return commentJpaRepository.deleteById(id)
    }

    override fun getListByPageNumberAndPageSize(pageNumber: Int, pageSize: Int): List<Comment> {
        return queryFactory.selectFrom(comment)
            .offset(((pageNumber - 1) * pageSize).toLong())
            .limit(pageSize.toLong())
            .orderBy(comment.id.desc())
            .fetch()
    }

    override fun getTotalPages(pageSize: Int): Int {
        return queryFactory
            .select(comment.count())
            .from(comment)
            .fetchOne()
            ?.let { ceil(it.toDouble() / pageSize).toInt() } ?: 0
    }
}