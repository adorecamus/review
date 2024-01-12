package com.codesophy.review.domain.users

import com.codesophy.review.domain.users.dtos.LoginArguments
import com.codesophy.review.domain.users.dtos.LoginDto
import com.codesophy.review.domain.users.dtos.SignUpArguments
import com.codesophy.review.domain.users.dtos.UserDto

interface UserService {
    fun login(loginArguments: LoginArguments): LoginDto
    fun signUp(signUpArguments: SignUpArguments): UserDto
}