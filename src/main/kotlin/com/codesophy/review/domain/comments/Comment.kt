package com.codesophy.review.domain.comments

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "comment")
class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var content: String,
        @Column
        val username: String,
        @Column
        val password: String,
){

        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now()

        fun changeContent(content: String){
                this.content = content
        }
        fun checkAuthentication(password: String){
                if(password != this.password){
                        throw Exception("wrong authentication not comment")
                }
        }
}