package com.example.java_lessons_app.dto;

import lombok.*;

@Builder
@Data
public class NewsAddDTO {
    private String title;
    private String description;
    private String text;
    private String date;
    private Long userId;
}
