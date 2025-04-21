package com.example.java_lessons_app.repositories;


import com.example.java_lessons_app.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByUserId(Long userId);
}
