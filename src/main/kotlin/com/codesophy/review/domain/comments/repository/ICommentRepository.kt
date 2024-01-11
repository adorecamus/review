package com.codesophy.review.domain.comments.repository

import com.codesophy.review.domain.comments.Comment

interface ICommentRepository {

    fun save(comment: Comment): Comment

    fun findByIdOrNull(id: Long): Comment?

    fun deleteById(id: Long)

    fun getListByPageNumberAndPageSize(pageNumber: Int, pageSize: Int): List<Comment>

    fun getTotalPages(pageSize: Int): Int
}