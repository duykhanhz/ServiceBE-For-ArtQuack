package com.duykhanhz.repository;

import com.duykhanhz.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByCourseId(int id);
}
