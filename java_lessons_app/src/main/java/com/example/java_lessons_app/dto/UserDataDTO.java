package com.example.java_lessons_app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDataDTO {
    private Long id;
    private String email;
    private String accessToken;
}
