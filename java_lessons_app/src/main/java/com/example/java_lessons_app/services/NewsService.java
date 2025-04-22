package com.example.java_lessons_app.services;


import com.example.java_lessons_app.dto.NewsAddDTO;
import com.example.java_lessons_app.dto.NewsDTO;
import com.example.java_lessons_app.entities.Image;
import com.example.java_lessons_app.entities.News;
import com.example.java_lessons_app.repositories.NewsRepository;
import com.example.java_lessons_app.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository, UsersRepository usersRepository) {
        this.newsRepository = newsRepository;
        this.usersRepository = usersRepository;
    }

    public NewsDTO mapToNewsDTO(News news){
        return NewsDTO.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .text(news.getText())
                .date(news.getDate())
                .userId(news.getUser().getId())
                .imagePathList(news.getImages().stream().map(Image::getImagePath).collect(Collectors.toList()))
                .build();

    }

    public News mapToEntity(NewsAddDTO newsAddDTO){
        List<Image>imageList = new ArrayList<>();

        return News.builder()
                .title(newsAddDTO.getTitle())
                .description(newsAddDTO.getDescription())
                .text(newsAddDTO.getText())
                .date(newsAddDTO.getDate())
                .isPublished(false)
                .user(usersRepository.findById(newsAddDTO.getUserId()).orElseThrow(EntityNotFoundException::new))
                .images(imageList)
                .build();
    }
    public void add(NewsAddDTO newsAddDTO){
        newsRepository.save(mapToEntity(newsAddDTO));
    }

    public List<NewsDTO> getAll(){
        return newsRepository.findAll().stream().map(this::mapToNewsDTO).collect(Collectors.toList());
    }

    public NewsDTO findById(Long id){
        return mapToNewsDTO(newsRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<NewsDTO> findByUserId(Long id){
        return newsRepository.findAllByUserId(id).stream().map(this::mapToNewsDTO).collect(Collectors.toList());
    }
    public Optional<News> update(Long id, NewsAddDTO newsAddDTO){
        Optional<News> newsOptional = newsRepository.findById(id);
        News newNews = mapToEntity(newsAddDTO);
        newsOptional.ifPresent(news ->{
            newsRepository.deleteById(id);
            newNews.setId(id);
            newsRepository.save(newNews);
        });
        return newsOptional;
    }

    public Optional<News> delete(Long id){
        Optional<News> newsOptional = newsRepository.findById(id);
        newsOptional.ifPresent(news ->{
            newsRepository.deleteById(id);
        });
        return newsOptional;
    }

}
