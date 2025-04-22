package com.example.java_lessons_app.controllers;

import com.example.java_lessons_app.dto.ImageAddDTO;
import com.example.java_lessons_app.entities.Image;
import com.example.java_lessons_app.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/image")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminImageController {

    private final ImageService service;

    @GetMapping
    public ResponseEntity<List<Image>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<Long>> findByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(service.findByNews(newsId));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ImageAddDTO imageAddDTO) throws IOException {
        service.add(imageAddDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> update(@RequestBody Image image, @PathVariable Long id) throws IOException {
        return service.update(image, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Image> deleted = service.delete(id);
        return deleted.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
