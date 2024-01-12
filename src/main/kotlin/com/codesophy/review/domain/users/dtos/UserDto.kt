package com.codesophy.review.domain.users.dtos

import com.codesophy.review.domain.users.User

data class UserDto(
        val id: Long?,
        val email: String,
        val nickname: String,
){
    companion object{
        fun to(user: User): UserDto{
            return UserDto(
                    id = user.id,
                    email = user.email,
                    nickname = user.nickname,
            )
        }
    }
}
