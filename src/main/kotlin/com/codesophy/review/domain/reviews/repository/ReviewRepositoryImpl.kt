package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.dto.ReviewDto
import com.codesophy.review.domain.reviews.dto.ReviewFeedArguments
import com.codesophy.review.domain.reviews.model.QReview
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.domain.users.model.QUser.user
import com.codesophy.review.infra.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import org.springframework.data.repository.findByIdOrNull

class ReviewRepositoryImpl(
    private val reviewJpaRepository: ReviewJpaRepository
) : IReviewRepository, QueryDslSupport() {

    private val review = QReview.review

    override fun findAll(): List<ReviewDto> {

        return queryFactory.select(
            Projections.constructor(
                ReviewDto::class.java,
                review.id,
                review.title,
                review.content,
                user.nickname,
                review.createdAt
            )
        )
            .from(review)
            .leftJoin(review.user, user)
            .fetch()
    }

    override fun findByIdOrNull(id: Long): Review? {
        return reviewJpaRepository.findByIdOrNull(id)
    }

    override fun save(review: Review): Review {
        return reviewJpaRepository.save(review)
    }

    override fun deleteById(id: Long) {
        queryFactory
            .delete(review)
            .where(review.id.eq(id))
            .execute()

        entityManager.clear()
    }

    override fun getPaginatedReviewList(
        cursorId: Long?,
        size: Int,
        reviewFeedArguments: ReviewFeedArguments
    ): List<ReviewDto> {

        val booleanBuilder = BooleanBuilder()
        cursorId?.let { booleanBuilder.and(review.id.lt(it)) }
        reviewFeedArguments.userId?.let { booleanBuilder.and(user.id.ne(it)) }
        reviewFeedArguments.title?.let { booleanBuilder.and(review.title.contains(it)) }
        reviewFeedArguments.nickname?.let { booleanBuilder.and(user.nickname.contains(it)) }

        return queryFactory.select(
            Projections.constructor(
                ReviewDto::class.java,
                review.id,
                review.title,
                review.content,
                user.nickname,
                review.createdAt
            )
        )
            .from(review)
            .leftJoin(review.user, user)
            .where(booleanBuilder)
            .orderBy(review.id.desc())
            .limit(size.toLong())
            .fetch()
    }

}