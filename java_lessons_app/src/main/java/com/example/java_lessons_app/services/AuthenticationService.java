package com.example.java_lessons_app.services;

import com.example.java_lessons_app.dto.UserAddDTO;
import com.example.java_lessons_app.dto.UserDataDTO;
import com.example.java_lessons_app.dto.UserLoginDTO;
import com.example.java_lessons_app.entities.User;
import com.example.java_lessons_app.enums.Role;
import com.example.java_lessons_app.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(UserAddDTO request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
    }

    @Transactional
    public UserDataDTO login(UserLoginDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        return UserDataDTO.builder()
                .email(user.getEmail())
                .id(user.getId())
                .accessToken(accessToken)
                .build();
    }
}