package com.codesophy.review.domain.reviews.controller

import com.codesophy.review.domain.reviews.dto.CreateReviewRequest
import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequest
import com.codesophy.review.domain.reviews.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService
){
    @GetMapping("/{reviewId}")
    fun getReview(@PathVariable reviewId: Long): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewById(reviewId))
    }
    //Review 단건 조회

    @GetMapping
    fun getReviewList(): ResponseEntity<List<ReviewResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getAllReviewList())
    }
    //Review 목록 조회

    @PostMapping
    fun createReview(@RequestBody createReviewRequest: CreateReviewRequest): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(createReviewRequest))
    }
    //Review 생성

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody updateReviewRequest: UpdateReviewRequest
    ) : ResponseEntity<ReviewResponse> {
        val request = UpdateReviewRequest(
            id = reviewId,
            title = updateReviewRequest.title,
            content = updateReviewRequest.content,
            username = updateReviewRequest.username,
            password = updateReviewRequest.password
        )

        val review: ReviewResponse = reviewService.updateReview(reviewId, request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(review)
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(@PathVariable reviewId: Long):ResponseEntity<Unit>{
        reviewService.deleteReview(reviewId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(null)
    }

}