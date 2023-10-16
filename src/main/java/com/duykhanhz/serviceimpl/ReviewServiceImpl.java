package com.duykhanhz.serviceimpl;

import com.duykhanhz.exceptions.CourseNotFoundException;
import com.duykhanhz.exceptions.ReviewNotFoundException;
import com.duykhanhz.model.dto.ReviewDto;
import com.duykhanhz.model.entity.Course;
import com.duykhanhz.model.entity.Review;
import com.duykhanhz.repository.CourseRepository;
import com.duykhanhz.repository.ReviewRepository;
import com.duykhanhz.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CourseRepository courseRepository;
@Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CourseRepository courseRepository) {
        this.reviewRepository = reviewRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ReviewDto createReview(int courseId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Pokemon with associated review not found"));

        review.setCourse(course);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }


    @Override
    public List<ReviewDto> getReviewsByCourseId(int id) {
        List<Review> reviews = reviewRepository.findByCourseId(id);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));

        if(review.getCourse().getId() != course.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a course");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int courseId, int reviewId, ReviewDto reviewDto) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Pokemon with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));

        if(review.getCourse().getId() != course.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a course");
        }

        review.setInstructorID(reviewDto.getInstructorID());
        review.setDateTime(reviewDto.getDateTime());
        review.setRate(reviewDto.getRate());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(int courseId, int reviewId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associate pokemon not found"));

        if(review.getCourse().getId() != course.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a course");
        }

        reviewRepository.delete(review);
    }
    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setInstructorID(reviewDto.getInstructorID());
        review.setDateTime(reviewDto.getDateTime());
        review.setRate(reviewDto.getRate());
        return review;
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setInstructorID(reviewDto.getInstructorID());
        reviewDto.setDateTime(reviewDto.getDateTime());
        reviewDto.setRate(reviewDto.getRate());
        return reviewDto;
    }
}
