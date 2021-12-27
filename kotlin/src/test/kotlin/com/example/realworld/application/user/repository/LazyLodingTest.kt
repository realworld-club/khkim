package com.example.realworld.application.user.repository

import com.example.realworld.application.article.domain.Article
import com.example.realworld.application.article.domain.ArticleRepository
import com.example.realworld.application.user.domain.User
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.hibernate.jpa.internal.util.PersistenceUtilHelper.isLoaded
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.persistence.Persistence.getPersistenceUtil
import javax.persistence.PersistenceUnitUtil

@DataJpaTest
class LazyLodingTest (
    @Autowired val userRepository: UserRepository,
    @Autowired val articleRepository: ArticleRepository,
    @Autowired val em:EntityManager,
    @Autowired val emf:EntityManagerFactory
        ){

    @BeforeEach
    fun before() {
        val user = User(email = "EMAIL", password = "PASSWORD")
        em.persist(user)

        val article = Article(title = "TITLE", slug = "TITLE", author = user)
        em.persist(article)

        em.flush()
        em.clear()
    }

    @Test
    fun `lazyLoding 테스트`() {
        //when
        val articleEntity = articleRepository.findByTitle("TITLE")
        //then
        assertThat(emf.persistenceUnitUtil.isLoaded(articleEntity.author)).isFalse()
        assertThat(articleEntity.author.email).isEqualTo("EMAIL")
        assertThat(articleEntity.author.password).isEqualTo("PASSWORD")
    }
}