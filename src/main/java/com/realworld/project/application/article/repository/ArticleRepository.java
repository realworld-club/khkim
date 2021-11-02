package com.realworld.project.application.article.repository;

import com.realworld.project.application.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findBySlug(String slug);

    @Query
    List<Article> findFeedArticles(String userEmail, Pageable pageable);
}
