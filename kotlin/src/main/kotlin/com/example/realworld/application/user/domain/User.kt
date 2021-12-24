package com.example.realworld.application.user.domain

import javax.persistence.*

@Entity
@Table(name = "user")
class User (
    @Id @GeneratedValue
    var id: Long? = null,

    var email: String,
    var password: String
    ) {

}