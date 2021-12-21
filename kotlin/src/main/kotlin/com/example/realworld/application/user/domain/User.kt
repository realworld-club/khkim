package com.example.realworld.application.user.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class User (
    @Id @GeneratedValue var id: Long? = null,
    var email: String,
    var password: String) {

}