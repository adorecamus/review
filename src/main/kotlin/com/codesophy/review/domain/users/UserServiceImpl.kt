package com.codesophy.review.domain.users

import com.codesophy.review.domain.users.dtos.SignUpDto
import com.codesophy.review.domain.users.dtos.UserDto
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
        private val userRepository: UserRepository
): UserService {
    override fun signUp(signUpDto: SignUpDto): UserDto {
        //  Email 중복되는지 확인, 중복된다면 throw Exception
        //  signUpDto가 User로 변환 후 DB에 저장, 비밀번호는 저장시 암호화
        TODO("Not yet implemented")
    }
}