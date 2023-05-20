package com.blog_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image", nullable = false, length = 2000)
    private String image;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "summary", nullable = false, length = 500)
    private String summary;
    @Column(name = "content", nullable = false, length = 3000)
    private String content;
    @Column
    private LocalDateTime postTime;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
