package com.example.realworld.application.article.domain

import com.example.realworld.application.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "ariticle")
class Article (
    @Id @GeneratedValue
    var id: Long? = null,

    var title: String,
    var slug: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var author: User
    ) {
}