package com.duykhanhz.controllers;
import com.duykhanhz.model.dto.CourseDto;
import com.duykhanhz.model.dto.CourseResponse;
import com.duykhanhz.model.dto.ReviewDto;
import com.duykhanhz.model.entity.Course;

import java.util.ArrayList;
import java.util.List;

import com.duykhanhz.model.entity.Review;
import com.duykhanhz.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;


@RestController
@RequestMapping("/api/")
public class CourseController {
    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    private ObjectMapper objectMapper;
    private Course course;
    private Review review;

    private ReviewDto reviewDto;
    private CourseDto courseDto;
    public ResponseEntity<CourseResponse> getCourses(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(courseService.getAllCourse(pageNo, pageSize), HttpStatus.OK);
    }
    @GetMapping("course/{id}")
    public ResponseEntity<CourseDto> courseDetail(@PathVariable int id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("course/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(courseService.createCourse(courseDto),HttpStatus.CREATED);
    }
    @PutMapping("course/{id}/update")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto, @PathVariable("id") int courseId){
        CourseDto response = courseService.updateCourse(courseDto,courseId);
        return null;
    }

    @DeleteMapping("course/{id}/delete")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") int courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("course delete", HttpStatus.OK);
    }
}
