package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository: JpaRepository<Comment, Long> {
}