package com.example.java_lessons_app.services;


import com.example.java_lessons_app.dto.ImageAddDTO;
import com.example.java_lessons_app.entities.Image;
import com.example.java_lessons_app.repositories.ImagesRepository;
import com.example.java_lessons_app.repositories.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImagesRepository imagesRepository;
    private final NewsRepository newsRepository;

    @Autowired
    public ImageService(ImagesRepository repository, NewsRepository newsRepository) {
        this.imagesRepository = repository;
        this.newsRepository = newsRepository;
    }

    private Image mapToEntity(ImageAddDTO imageAddDTO){

        return Image.builder()
                .image(imageAddDTO.getImage())
                .news(newsRepository.findById(imageAddDTO.getNewsId()).orElseThrow(EntityNotFoundException::new))
                .build();

    }

    public List<Image> getAll(){
        return imagesRepository.findAll();
    }

    public void add(ImageAddDTO imageAddDTO) throws IOException {
        imagesRepository.save(mapToEntity(imageAddDTO));
    }

    public Optional<Image> delete(Long id){
        Optional<Image> imageOptional = imagesRepository.findById(id);
        imageOptional.ifPresent(image -> {
            imagesRepository.deleteById(id);
        });
        return imageOptional;
    }

    public Image getById(Long id) {
        return imagesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + id + " not found"));
    }

    public List<Image> findByNews(Long newsId){
        return imagesRepository.findAllByNewsId(newsId);
    }

    public Optional<Image> update(Image image, Long id) throws IOException {
        Optional<Image> imageOptional = imagesRepository.findById(id);
        Image newImage = new Image();
        imageOptional.ifPresent(x -> {
            imagesRepository.deleteById(id);
            newImage.setId(id);
            imagesRepository.save(newImage);
        });
        return imageOptional;
    }



}
