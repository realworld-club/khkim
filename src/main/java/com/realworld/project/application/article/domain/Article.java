package com.realworld.project.application.article.domain;

import com.realworld.project.application.article.api.dto.RequestCreateArticle;
import com.realworld.project.application.article.service.SlugHelper;
import com.realworld.project.application.user.domain.User;
import com.realworld.project.core.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "article")
public class Article extends BaseEntity {

    @Id @Column(name = "article_id")
    @GeneratedValue
    private Long id;

    private String slug;
    private String title;
    private String description;
    private String body;
    private boolean favorited;
    private int favoritesCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_tag",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @Builder
    public Article(String slug, String title, String description, String body, boolean favorited, int favoritesCount, User user, Set<Tag> tags) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.user = user;
        this.tags = tags;
    }

    public static Article of(RequestCreateArticle createArticle, User user) {
        Set<Tag> tags = createArticle.getTagList().stream()
                .map(Tag::new)
                .collect(Collectors.toSet());

        return Article.builder()
                .title(createArticle.getTitle())
                .slug(SlugHelper.convert(createArticle.getTitle()))
                .description(createArticle.getDescription())
                .body(createArticle.getBody())
                .favoritesCount(0)
                .favorited(false)
                .tags(tags)
                .user(user)
                .build();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getArticles().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getArticles().remove(this);
    }

}
