package com.codesophy.review.domain.users.dto

data class SignUpArguments(
        val email: String,
        val password: String,
        val nickname: String,
)