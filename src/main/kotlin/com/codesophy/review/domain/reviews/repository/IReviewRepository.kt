package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.model.Review

interface IReviewRepository {

    fun findAll(): List<Review>

    fun findByIdOrIdNull(id: Long): Review?

    fun save(review: Review): Review

    fun deleteById(id: Long)

    fun getLimitedReviewsLessThanId(id: Long?, size: Int): List<Review>
}