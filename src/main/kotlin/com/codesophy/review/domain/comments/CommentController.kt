package com.codesophy.review.domain.comments

import com.codesophy.review.domain.comments.dtos.CheckPasswordArguments
import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.pagination.PageResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @PutMapping("/{commentId}")
    fun updateComment(
            @PathVariable commentId: Long,
            @RequestBody updateCommentArguments: UpdateCommentArguments
    ): ResponseEntity<CommentDto>{
        val arguments = UpdateCommentArguments(
                id = commentId,
                content = updateCommentArguments.content
        )
        val comment = commentService.updateComment(arguments)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comment)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
            @PathVariable commentId: Long
    ): ResponseEntity<Unit>{
        commentService.deleteComment(commentId)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null)
    }

    @PostMapping("/{commentId}/password")
    fun checkPassword(
            @PathVariable commentId: Long,
            @RequestBody checkPasswordArguments: CheckPasswordArguments
    ): ResponseEntity<Unit> {
        val arguments = CheckPasswordArguments(
                id = commentId,
                password = checkPasswordArguments.password
        )
        commentService.checkPassword(arguments)
        return ResponseEntity
                .status(HttpStatus.OK)
                .build()
    }

    @GetMapping
    fun getPaginatedCommentList(
            @RequestParam(defaultValue = "1") pageNumber: Int,
            @RequestParam(defaultValue = "5") pageSize: Int
    ): ResponseEntity<PageResponse<CommentDto>> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getPaginatedCommentList(pageNumber, pageSize))
    }
}