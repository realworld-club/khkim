package com.example.realworld.application.article.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
    fun findByTitle(title: String): Article
}