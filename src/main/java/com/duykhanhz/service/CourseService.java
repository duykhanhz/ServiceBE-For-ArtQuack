package com.duykhanhz.service;

import com.duykhanhz.model.dto.CourseDto;
import com.duykhanhz.model.dto.CourseResponse;
import com.duykhanhz.model.entity.Course;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);
    CourseResponse getAllCourse(int pageNo, int pageSize);
    CourseDto getCourseById(int id);
    CourseDto updateCourse(CourseDto courseDto, int id);

    void deleteCourse(int id);


}
