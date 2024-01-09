package com.codesophy.review.controller

import com.codesophy.review.dto.ReviewResponse
import com.codesophy.review.dto.UpdateReviewRequset
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/review")
@RestController
class ReviewContoller {

    @PutMapping("/{reveiwId}")
    fun updateReview(
        @PathVariable courseId: Long,
        @RequestBody updateReviewRequset: UpdateReviewRequset
    ) :ResponseEntity<ReviewResponse> {
        TODO()
    }


    @DeleteMapping("/{reveiwId}")
    fun deleteReview(@PathVariable courseId: Long){
        TODO()
    }
}