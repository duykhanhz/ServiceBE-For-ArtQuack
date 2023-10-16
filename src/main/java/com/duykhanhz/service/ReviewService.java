package com.duykhanhz.service;

import com.duykhanhz.model.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int courseId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByCourseId(int id);
    ReviewDto getReviewById(int reviewId, int courseId);
    ReviewDto updateReview(int courseId, int reviewId, ReviewDto reviewDto);
    void deleteReview(int courseId, int reviewId);
}
