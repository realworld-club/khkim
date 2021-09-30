package com.realworld.project.article.domain;

import com.realworld.project.user.domain.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "article")
public class Article {

    @Id @Column(name = "article_id")
    @GeneratedValue
    private Long id;

    private String slug;
    private String title;
    private String description;
    private String body;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private Set<Tag> tagList = new HashSet<>();
    private boolean favorited;
    private int favoritesCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
}
