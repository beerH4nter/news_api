package com.example.java_lessons_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageAddDTO {
    private String image;
    private Long newsId;
}
