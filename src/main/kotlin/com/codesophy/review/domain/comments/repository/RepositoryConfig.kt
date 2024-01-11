package com.codesophy.review.domain.comments.repository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfig {

    @Bean
    fun commentRespository(
        commentJpaRespository: CommentJpaRepository
    ): ICommentRepository {
        return CommentRepositoryImpl(commentJpaRespository)
    }
}