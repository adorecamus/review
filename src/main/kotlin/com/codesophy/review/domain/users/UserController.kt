package com.codesophy.review.domain.users

import com.codesophy.review.domain.users.dtos.SignUpDto
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

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<UserDto>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.signUp(signUpDto))
    }
}