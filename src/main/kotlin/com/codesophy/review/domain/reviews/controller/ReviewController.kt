package com.codesophy.review.domain.reviews.controller

import com.codesophy.review.domain.pagination.CursorResponse
import com.codesophy.review.domain.reviews.service.ReviewService
import com.codesophy.review.domain.reviews.dto.*
import com.codesophy.review.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService
){
    @GetMapping("/{reviewId}")
    fun getReview(@PathVariable reviewId: Long): ResponseEntity<ReviewDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewById(reviewId))
    }
    //Review 단건 조회

    @GetMapping
    fun getReviewList(): ResponseEntity<List<ReviewDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getAllReviewList())
    }
    //Review 목록 조회

    @PostMapping
    fun createReview(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody createReviewRequest: CreateReviewArguments
    ): ResponseEntity<ReviewDto> {
        val request = CreateReviewArguments(
            title = createReviewRequest.title,
            content = createReviewRequest.content,
            userId = userPrincipal.id
        )
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(request))
    }
    //Review 생성

    @PutMapping("/{reviewId}")
    fun updateReview(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable reviewId: Long,
        @RequestBody updateReviewRequest: UpdateReviewArguments
    ) : ResponseEntity<ReviewDto> {
        val request = UpdateReviewArguments(
            id = reviewId,
            title = updateReviewRequest.title,
            content = updateReviewRequest.content,
            userId = userPrincipal.id
        )

        val review: ReviewDto = reviewService.updateReview(request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(review)
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable reviewId: Long
    ): ResponseEntity<Unit> {
        val request = DeleteReviewArguments(
            id = reviewId,
            userId = userPrincipal.id
        )

        reviewService.deleteReview(request)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(null)
    }

    @GetMapping("/newsfeed")
    fun getPaginatedReviewList(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestParam cursorId: Long?,
        @RequestParam(defaultValue = "15") pageSize: Int,
        reviewFeedArguments: ReviewFeedArguments
    ): ResponseEntity<CursorResponse<ReviewDto>> {

        val arguments = ReviewFeedArguments(
            userId = userPrincipal.id,
            title = reviewFeedArguments.title,
            nickname = reviewFeedArguments.nickname
        )

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getPaginatedReviewList(cursorId, pageSize, arguments))
    }

}