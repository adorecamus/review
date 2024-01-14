package com.codesophy.review.domain.pagination

data class CursorResponse<T>(
    val dtoList: List<T>,
    val hasNext: Boolean
)
