package com.codesophy.review.comments

import com.codesophy.review.comments.dtos.CommentDto
import com.codesophy.review.comments.dtos.WriteCommentArguments
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/comments")
@RestController
class CommentController(
        private val commentService: CommentService
) {

    @PostMapping
    fun writeComment(
            @RequestBody writeCommentArguments: WriteCommentArguments
    ): ResponseEntity<CommentDto>{
        val result = commentService.writeComment(writeCommentArguments)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result)
    }
}