package com.codesophy.review.domain.users

import com.codesophy.review.domain.users.dtos.LoginArguments
import com.codesophy.review.domain.users.dtos.LoginDto
import com.codesophy.review.domain.users.dtos.SignUpArguments
import com.codesophy.review.domain.users.dtos.UserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginArguments: LoginArguments): ResponseEntity<LoginDto>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(loginArguments))
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpArguments: SignUpArguments): ResponseEntity<UserDto>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.signUp(signUpArguments))
    }
}