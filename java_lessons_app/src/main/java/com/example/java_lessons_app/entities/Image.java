package com.example.java_lessons_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Type;
import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "images")
@ToString
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Image path can not be empty")
    private String image;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

}
