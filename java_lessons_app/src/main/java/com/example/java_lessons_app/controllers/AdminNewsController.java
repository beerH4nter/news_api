package com.example.java_lessons_app.controllers;

import com.example.java_lessons_app.dto.NewsAddDTO;
import com.example.java_lessons_app.dto.NewsDTO;
import com.example.java_lessons_app.entities.News;
import com.example.java_lessons_app.services.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/news")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminNewsController {

    private final NewsService service;

    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NewsDTO>> findByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid NewsAddDTO newsAddDTO){
        service.add(newsAddDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> update(@RequestBody @Valid NewsAddDTO newsAddDTO, @PathVariable Long id){
        return service.update(id, newsAddDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

