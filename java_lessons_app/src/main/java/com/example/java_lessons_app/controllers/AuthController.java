package com.example.java_lessons_app.controllers;


import com.example.java_lessons_app.dto.UserAddDTO;
import com.example.java_lessons_app.dto.UserDataDTO;
import com.example.java_lessons_app.dto.UserLoginDTO;
import com.example.java_lessons_app.dto.UserLoginResponseDTO;
import com.example.java_lessons_app.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAddDTO request) {
        service.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO request) {
        UserDataDTO dto = service.login(request);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, dto.getAccessToken());
        return ResponseEntity.ok().headers(headers).body(
                UserLoginResponseDTO.builder()
                        .id(dto.getId())
                        .email(dto.getEmail())
                .build()
        );
    }
}
