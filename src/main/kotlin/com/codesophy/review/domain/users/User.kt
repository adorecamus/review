package com.codesophy.review.domain.users

import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var username: String,
        @Column
        val email: String,
        @Column
        val password: String,
)