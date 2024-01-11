package com.codesophy.review.domain.reviews.controller

import com.codesophy.review.domain.reviews.dto.ReviewResponse
import com.codesophy.review.domain.reviews.dto.UpdateReviewRequset
import com.codesophy.review.domain.exception.ModelNotFoundException
import com.codesophy.review.domain.reviews.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/reviews")
@RestController
class ReviewContoller(
    private val reviewService: ReviewService
) {

    @PutMapping("/{reveiwId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody updateReviewRequset: UpdateReviewRequset
    ) : ResponseEntity<ReviewResponse> {
        val requset = UpdateReviewRequset(
            id = reviewId,
            title = updateReviewRequset.title,
            content = updateReviewRequset.content,
            username = updateReviewRequset.username
        )

        val review: ReviewResponse = reviewService.updateReview(reviewId, requset)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(review)
    }


    @DeleteMapping("/{reveiwId}")
    fun deleteReview(@PathVariable reviewId: Long):ResponseEntity<Unit>{
        reviewService.deleteReview(reviewId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(null)
    }



}