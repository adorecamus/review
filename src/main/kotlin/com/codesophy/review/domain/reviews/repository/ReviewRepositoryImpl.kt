package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.model.Review
import com.codesophy.review.infra.querydsl.QueryDslSupport

class ReviewRepositoryImpl(
    private val reviewJpaRepository: ReviewJpaRepository
) : IReviewRepository {
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
}