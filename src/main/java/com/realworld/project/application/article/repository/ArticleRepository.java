package com.realworld.project.application.article.repository;

import com.realworld.project.application.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findBySlug(String slug);
}
