package com.example.java_lessons_app.repositories;


import com.example.java_lessons_app.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByNewsId(Long newsId);

}
