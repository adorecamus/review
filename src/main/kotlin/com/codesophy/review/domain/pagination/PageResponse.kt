package com.codesophy.review.domain.pagination

data class PageResponse<T>(
    val dtoList: List<T>,
    val totalPages: Int
)
