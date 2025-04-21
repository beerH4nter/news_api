package com.example.java_lessons_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "title can not be empty")
    private String title;

    @NotEmpty(message = "description can not be empty")
    private String description;

    @NotEmpty(message = "text can not be empty")
    @Column(length = 16384)
    private String text;

    @NotEmpty(message = "date can not be empty")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$")
    private String date;

    private Boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;


}
