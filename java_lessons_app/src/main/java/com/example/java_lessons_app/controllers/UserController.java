package com.example.java_lessons_app.controllers;

import com.example.java_lessons_app.dto.UserAddDTO;
import com.example.java_lessons_app.dto.UserDTO;
import com.example.java_lessons_app.entities.User;
import com.example.java_lessons_app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @PostMapping
    public void add(@RequestBody @Valid UserAddDTO userAddDTO){
        service.add(userAddDTO);
    }

    @PostMapping("/{id}")
    public Optional<User> update(@PathVariable Long id, @RequestBody @Valid UserAddDTO userAddDTO){
        return service.update(userAddDTO, id);
    }

    @DeleteMapping("/{id}")
    public Optional<User> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
