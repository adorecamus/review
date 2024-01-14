package com.codesophy.review.domain.users.service

import com.codesophy.review.domain.users.dto.LoginArguments
import com.codesophy.review.domain.users.dto.LoginDto
import com.codesophy.review.domain.users.dto.SignUpArguments
import com.codesophy.review.domain.users.dto.UserDto

interface UserService {
    fun login(loginArguments: LoginArguments): LoginDto
    fun signUp(signUpArguments: SignUpArguments): UserDto
}