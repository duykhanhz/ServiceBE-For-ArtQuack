package com.duykhanhz.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReviewDto {
    private int id;
    private int artworkID;
    private int userID;
    private int instructorID;
    private int rate;
    private String comment;
    private LocalDateTime dateTime;
}
