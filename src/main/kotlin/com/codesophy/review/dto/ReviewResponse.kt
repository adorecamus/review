package com.codesophy.review.dto

import java.time.ZonedDateTime
import java.util.Date

data class ReviewResponse(
    val id: Long,
    val title: String,
    val content: String,
    val username: String,
    val createAt: ZonedDateTime
)
