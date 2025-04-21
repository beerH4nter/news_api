package com.example.java_lessons_app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserAddDTO {
    private String email;
    private String password;
}
