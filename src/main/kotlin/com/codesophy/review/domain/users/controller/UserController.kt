package com.codesophy.review.domain.users.controller

import com.codesophy.review.domain.users.service.UserService
import com.codesophy.review.domain.users.dto.LoginArguments
import com.codesophy.review.domain.users.dto.LoginDto
import com.codesophy.review.domain.users.dto.SignUpArguments
import com.codesophy.review.domain.users.dto.UserDto
import com.codesophy.review.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
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

    @PutMapping("/logout")
    fun logout(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit> {
        userService.logout(userPrincipal.email)
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }

}