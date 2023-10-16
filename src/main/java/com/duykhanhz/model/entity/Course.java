package com.duykhanhz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private int instructorID;
    private int cateID;
    private int levelID;
    private int reviewID;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private float price;
    private int viewer;
    private int buyer;
    private int rate;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>();
}