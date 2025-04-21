package com.example.java_lessons_app.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private String email;
    private String password;
    private List<Long> newsIdList;
}
