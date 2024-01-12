package com.codesophy.review.domain.users.dtos

data class SignUpArguments(
        val email: String,
        val password: String,
        val username: String,
)