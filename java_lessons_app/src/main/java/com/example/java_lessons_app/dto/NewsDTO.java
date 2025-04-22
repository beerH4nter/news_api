package com.example.java_lessons_app.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsDTO {
    private Long id;
    private String title;
    private String description;
    private String text;
    private String date;
    private Long userId;
    private List<String> imagePathList; //TODO: переписать под path
}
