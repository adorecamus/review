package com.codesophy.review.domain.comments.controller

import com.codesophy.review.domain.comments.service.CommentService
import com.codesophy.review.domain.comments.dtos.CommentDto
import com.codesophy.review.domain.comments.dtos.DeleteCommentArgument
import com.codesophy.review.domain.comments.dtos.UpdateCommentArguments
import com.codesophy.review.domain.comments.dtos.WriteCommentArguments
import com.codesophy.review.domain.pagination.PageResponse
import com.codesophy.review.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/reviews/{reviewId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping
    fun writeComment(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @PathVariable reviewId: Long,
            @RequestBody writeCommentArguments: WriteCommentArguments
    ): ResponseEntity<CommentDto>{
        val request = WriteCommentArguments(
                content = writeCommentArguments.content,
                userId = userPrincipal.id
        )
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.writeComment(reviewId,request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @PathVariable reviewId: Long,
            @PathVariable commentId: Long,
            @RequestBody updateCommentArguments: UpdateCommentArguments
    ): ResponseEntity<CommentDto>{
        val arguments = UpdateCommentArguments(
                id = commentId,
                content = updateCommentArguments.content,
                userId = userPrincipal.id
        )
        val comment = commentService.updateComment(reviewId, arguments)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comment)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @PathVariable reviewId: Long,
            @PathVariable commentId: Long
    ): ResponseEntity<Unit>{
        val request = DeleteCommentArgument(
                id = commentId,
                userId = userPrincipal.id
        )

        commentService.deleteComment(request)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null)
    }

    @GetMapping
    fun getPaginatedCommentList(
            @PathVariable reviewId: Long,
            @RequestParam(defaultValue = "1") pageNumber: Int,
            @RequestParam(defaultValue = "5") pageSize: Int
    ): ResponseEntity<PageResponse<CommentDto>> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getPaginatedCommentList(reviewId, pageNumber, pageSize))
    }
}