package com.codesophy.review.domain.users

import com.codesophy.review.domain.users.dtos.SignUpDto
import com.codesophy.review.domain.users.dtos.UserDto

interface UserService {
    fun signUp(signUpDto: SignUpDto): UserDto
}