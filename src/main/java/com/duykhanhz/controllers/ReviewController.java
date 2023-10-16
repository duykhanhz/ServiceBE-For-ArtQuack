package com.duykhanhz.controllers;


import com.duykhanhz.model.dto.ReviewDto;
import com.duykhanhz.model.entity.Course;
import com.duykhanhz.model.entity.Review;
import com.duykhanhz.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){

        this.reviewService = reviewService;
    }

    @PostMapping("/course/{courseId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "courseId") int courseId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(courseId, reviewDto), HttpStatus.CREATED);
    }
    @GetMapping("/course/{courseId}/reviews")
    public List<ReviewDto> getReviewsByCourseId(@PathVariable(value = "courseId") int courseId) {
        return reviewService.getReviewsByCourseId(courseId);
    }
    @GetMapping("/course/{courseId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "courseId") int courseId, @PathVariable(value = "id") int reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(courseId, reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }
    @PutMapping("/course/{courseId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "courseId") int courseId, @PathVariable(value = "id") int reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(courseId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }
    @DeleteMapping("/course/{courseId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "courseId") int courseId, @PathVariable(value = "id") int reviewId) {
        reviewService.deleteReview(courseId, reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
