package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.model.Comment
import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.model.QComment
import com.codesophy.review.domain.users.model.QUser.user
import com.codesophy.review.infra.querydsl.QueryDslSupport
import com.querydsl.core.types.Projections
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

    override fun getListByPageNumberAndPageSize(reviewId: Long, pageNumber: Int, pageSize: Int): List<CommentDto> {
        return queryFactory.select(
            Projections.constructor(
                CommentDto::class.java,
                comment.id,
                user.nickname,
                comment.content,
                comment.createdAt
            )
        )
            .from(comment)
            .leftJoin(comment.user, user)
            .where(comment.review.id.eq(reviewId))
            .offset(((pageNumber - 1) * pageSize).toLong())
            .limit(pageSize.toLong())
            .orderBy(comment.id.desc())
            .fetch()
    }

    override fun getTotalPages(reviewId: Long, pageSize: Int): Int {
        return queryFactory
            .select(comment.count())
            .from(comment)
            .where(comment.review.id.eq(reviewId))
            .fetchOne()
            ?.let { ceil(it.toDouble() / pageSize).toInt() } ?: 0
    }
}