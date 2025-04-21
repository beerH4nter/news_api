package com.example.java_lessons_app.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserLoginResponseDTO {
    private Long id;
    private String email;
}
