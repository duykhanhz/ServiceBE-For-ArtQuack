package com.duykhanhz.serviceimpl;

import com.duykhanhz.exceptions.CourseNotFoundException;
import com.duykhanhz.model.dto.CourseDto;
import com.duykhanhz.model.dto.CourseResponse;
import com.duykhanhz.model.entity.Course;
import com.duykhanhz.repository.CourseRepository;
import com.duykhanhz.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository =courseRepository;
    }
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setCode(courseDto.getCode());
        course.setInstructorID(courseDto.getInstructorID());
        course.setCateID(courseDto.getCateID());
        course.setLevelID(courseDto.getLevelID());
        course.setReviewID(courseDto.getReviewID());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setViewer(courseDto.getViewer());
        course.setBuyer(courseDto.getBuyer());
        course.setRate(courseDto.getRate());
        Course newCourse = courseRepository.save(course);

        CourseDto courseResponse = new CourseDto();
        courseResponse.setId(newCourse.getId());
        courseResponse.setCode(newCourse.getCode());
        courseResponse.setInstructorID(newCourse.getInstructorID());
        courseResponse.setCateID(newCourse.getCateID());
        courseResponse.setReviewID(newCourse.getReviewID());
        courseResponse.setName(newCourse.getName());
        courseResponse.setDescription(newCourse.getDescription());
        courseResponse.setPrice(newCourse.getPrice());
        courseResponse.setBuyer(newCourse.getBuyer());
        courseResponse.setReviewID(newCourse.getReviewID());
        courseResponse.setBuyer(newCourse.getBuyer());
        courseResponse.setRate(newCourse.getRate());
        return courseResponse;
    }

    @Override
    public CourseResponse getAllCourse(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Course> courses = courseRepository.findAll(pageable);
        List<Course> listOfCourse = courses.getContent();
        List<CourseDto> content = listOfCourse.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setContent(content);
        courseResponse.setPageNo(courses.getNumber());
        courseResponse.setPageSize(courses.getSize());
        courseResponse.setTotalElements(courses.getTotalElements());
        courseResponse.setTotalPages(courses.getTotalPages());
        courseResponse.setLast(courses.isLast());

        return courseResponse;
    }


    @Override
    public CourseDto getCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));

        return mapToDto(course);
    }
    @Override
    public CourseDto updateCourse(CourseDto courseDto, int id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException("Course could not be updated"));

        course.setName(courseDto.getName());

        return null;
    }

    @Override
    public void deleteCourse(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be delete"));
        courseRepository.delete(course);
    }


    private CourseDto mapToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCode(course.getCode());
        courseDto.setInstructorID(course.getInstructorID());
        courseDto.setCateID(course.getCateID());
        courseDto.setLevelID(course.getLevelID());
        courseDto.setReviewID(course.getReviewID());
        courseDto.setDescription(course.getDescription());
        courseDto.setPrice(course.getPrice());
        courseDto.setViewer(course.getViewer());
        courseDto.setBuyer(course.getBuyer());
        courseDto.setRate(course.getRate());

        return courseDto;
    }
    private Course mapToEntity(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setCode(courseDto.getCode());
        return course;
    }
}
