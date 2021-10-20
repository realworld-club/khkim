package com.realworld.project.application.article.repository;

import com.realworld.project.application.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
