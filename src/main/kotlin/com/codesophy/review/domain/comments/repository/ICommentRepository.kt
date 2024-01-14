package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.model.Comment
import com.codesophy.review.domain.comments.dtos.CommentDto

interface ICommentRepository {

    fun save(comment: Comment): Comment

    fun findByIdOrNull(id: Long): Comment?

    fun deleteById(id: Long)

    fun getListByPageNumberAndPageSize(reviewId: Long, pageNumber: Int, pageSize: Int): List<CommentDto>

    fun getTotalPages(reviewId: Long, pageSize: Int): Int
}