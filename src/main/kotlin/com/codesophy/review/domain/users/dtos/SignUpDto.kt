package com.codesophy.review.domain.users.dtos

data class SignUpDto(
        val email: String,
        val password: String,
        val username: String,
)