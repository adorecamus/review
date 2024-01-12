package com.codesophy.review.domain.users

import com.codesophy.review.domain.exception.InvalidCredentialException
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.users.dtos.LoginArguments
import com.codesophy.review.domain.users.dtos.LoginDto
import com.codesophy.review.domain.users.dtos.SignUpArguments
import com.codesophy.review.domain.users.dtos.UserDto
import com.codesophy.review.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder : PasswordEncoder,
        private val jwtPlugin: JwtPlugin
): UserService {
    override fun login(loginArguments: LoginArguments): LoginDto {
        val foundUser = userRepository.findByEmail(loginArguments.email) ?: throw ModelNotFoundException("User", null)

        if (!passwordEncoder.matches(loginArguments.password, foundUser.password) ) {
            throw InvalidCredentialException()
        }

        return LoginDto(
                accessToken = jwtPlugin.generateAccessToken(
                        subject = foundUser.id.toString(),
                        email = foundUser.email
                )
        )
    }

    override fun signUp(signUpArguments: SignUpArguments): UserDto {
        if (userRepository.existsByEmail(signUpArguments.email)) {
            throw IllegalStateException("Email is already in use")
        }

        val result = userRepository.save(User(
                email = signUpArguments.email,
                password = passwordEncoder.encode(signUpArguments.password),// 암호화
                nickname = signUpArguments.nickname
        ))
        return UserDto.to(result)
    }
}