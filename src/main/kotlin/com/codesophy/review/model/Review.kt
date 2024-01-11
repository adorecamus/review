package com.codesophy.review.model

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "review")
class Review (
    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "username")
    var username: String,

    @Column(name = "create_at")
    var createAt: ZonedDateTime = ZonedDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
 ) {


}