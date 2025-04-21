package com.example.java_lessons_app.controllers;


import com.example.java_lessons_app.dto.NewsAddDTO;
import com.example.java_lessons_app.dto.NewsDTO;
import com.example.java_lessons_app.entities.News;
import com.example.java_lessons_app.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService service;

    @Autowired
    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public List<NewsDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @GetMapping("/findByUser/{userId}")
    public ResponseEntity<List<NewsDTO>> findByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(
                service.findByUserId(userId)
        );
    }

    @PostMapping
    public void add(@RequestBody @Valid NewsAddDTO newsAddDTO){
        service.add(newsAddDTO);
    }

    @PostMapping("/{id}")
    public Optional<News> update(@RequestBody @Valid NewsAddDTO newsAddDTO, @PathVariable Long id){
        return service.update(id,newsAddDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
