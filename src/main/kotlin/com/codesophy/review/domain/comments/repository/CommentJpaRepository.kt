package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository: JpaRepository<Comment, Long> {

    fun countByReviewId(reviewId: Long): Long
}