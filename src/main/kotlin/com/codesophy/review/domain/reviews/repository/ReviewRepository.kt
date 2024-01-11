package com.codesophy.review.domain.reviews.repository

import com.codesophy.review.domain.reviews.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository: JpaRepository<Review, Long> {
}