package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewJpaRepository: JpaRepository<Review, Long> {

    fun findByIdOrIdNull(id: Long): Review?
}