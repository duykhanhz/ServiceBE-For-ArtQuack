package com.duykhanhz.repository;

import com.duykhanhz.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,String> {
    Optional<Course> findById(int id);
}
