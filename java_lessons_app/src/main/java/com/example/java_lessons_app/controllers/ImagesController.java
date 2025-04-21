package com.example.java_lessons_app.controllers;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/img")
public class ImagesController {
    @GetMapping("/{imagePath}")
    ResponseEntity<byte[]> getImage(@PathVariable String imagePath) throws IOException {
        String dirPath = "src/main/resources/static/images/";
        Path path = Paths.get(dirPath + imagePath);
        return ResponseEntity.ok(Files.readAllBytes(path));
    }
}
