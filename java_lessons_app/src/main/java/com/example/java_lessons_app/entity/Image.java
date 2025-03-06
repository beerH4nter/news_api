package com.example.java_lessons_app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob  // Используем @Lob для хранения больших данных
    @Column(columnDefinition = "BYTEA")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;
}
