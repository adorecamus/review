package com.codesophy.review.domain.config

import com.codesophy.review.domain.comments.repository.CommentJpaRepository
import com.codesophy.review.domain.comments.repository.CommentRepositoryImpl
import com.codesophy.review.domain.comments.repository.ICommentRepository
import com.codesophy.review.domain.reviews.repository.IReviewRepository
import com.codesophy.review.domain.reviews.repository.ReviewJpaRepository
import com.codesophy.review.domain.reviews.repository.ReviewRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfig {

    @Bean
    fun reviewRepository(
        reviewJpaRepository: ReviewJpaRepository
    ): IReviewRepository {
        return ReviewRepositoryImpl(reviewJpaRepository)
    }

    @Bean
    fun commentRespository(
        commentJpaRespository: CommentJpaRepository
    ): ICommentRepository {
        return CommentRepositoryImpl(commentJpaRespository)
    }

}