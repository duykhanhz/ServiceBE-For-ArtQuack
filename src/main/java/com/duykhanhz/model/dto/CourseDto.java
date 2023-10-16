package com.duykhanhz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
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
}
