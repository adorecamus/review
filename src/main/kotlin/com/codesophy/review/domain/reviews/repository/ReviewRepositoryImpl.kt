package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.model.QReview
import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.infra.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder

class ReviewRepositoryImpl(
    private val reviewJpaRepository: ReviewJpaRepository
) : IReviewRepository, QueryDslSupport() {

    private val review = QReview.review

    override fun findAll(): List<Review> {
        return reviewJpaRepository.findAll()
    }

    override fun findByIdOrIdNull(id: Long): Review? {
        return reviewJpaRepository.findByIdOrIdNull(id)
    }

    override fun save(review: Review): Review {
        return reviewJpaRepository.save(review)
    }

    override fun deleteById(id: Long) {
        reviewJpaRepository.deleteById(id)
    }

    override fun getLimitedReviewsLessThanId(id: Long?, size: Int): List<Review> {

        val booleanBuilder = BooleanBuilder()
        id?.let { booleanBuilder.and(review.id.lt(it)) }

        return queryFactory.selectFrom(review)
            .where(booleanBuilder)
            .orderBy(review.id.desc())
            .limit(size.toLong())
            .fetch()
    }

}