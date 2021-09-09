package com.realworld.project.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    void deleteByAuthorId(Long id);
}