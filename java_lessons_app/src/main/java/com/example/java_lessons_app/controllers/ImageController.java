package com.example.java_lessons_app.controllers;


import com.example.java_lessons_app.dto.ImageAddDTO;
import com.example.java_lessons_app.entities.Image;
import com.example.java_lessons_app.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService service;

    @Autowired
    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<String> getImagePath(@PathVariable Long id){
//        return ResponseEntity.ok(service.getById(id));
//    }

    @GetMapping("/findByNews/{newsId}")
    public ResponseEntity<List<Long>> findByNewsId(@PathVariable Long newsId){
        return ResponseEntity.ok(
                service.findByNews(newsId)
        );
    }

    @GetMapping("/{path}")
    ResponseEntity<byte[]> getImage(@PathVariable String path) throws IOException {
        String dirPath = "src/main/resources/static/images/";
        Path fullPath = Paths.get(dirPath + path);
        return ResponseEntity.ok(Files.readAllBytes(fullPath));
    }
    
    @PostMapping
    public void add(@RequestBody ImageAddDTO imageAddDTO) throws IOException {
        service.add(imageAddDTO);
    }

    @DeleteMapping("/{id}")
    public Optional<Image> delete(@PathVariable Long id){
        return service.delete(id);
    }

    @PostMapping("/{id}")
    public Optional<Image> update(@RequestBody Image image, @PathVariable Long id) throws IOException {
        return service.update(image, id);

    }
}
