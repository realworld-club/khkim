package com.example.realworld.application.user.repository

import com.example.realworld.application.article.domain.Article
import com.example.realworld.application.article.domain.ArticleRepository
import com.example.realworld.application.user.domain.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class LazyLodingTest (
    @Autowired val userRepository: UserRepository,
    @Autowired val articleRepository: ArticleRepository
        ){

    @Test
    fun `lazyLoding 테스트`() {
        //given
        val user = User(email = "email", password = "password")
        val article = Article(title = "title", slug = "title", author = user)
        userRepository.save(user);
        articleRepository.save(article)
        //when
        val articleEntity = articleRepository.findByTitle("title")
        //then
        println(articleEntity.author)
    }
}