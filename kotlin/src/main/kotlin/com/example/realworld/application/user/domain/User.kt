package com.example.realworld.application.user.domain

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import javax.persistence.*

@Entity
@Table(name = "user")
class User (
    @Id @GeneratedValue
    var id: Long? = null,

    var email: String,
    var password: String?
    ) {
    companion object {
        fun from(registerUser: RequestRegisterUser): User {
            return User(email = registerUser.email,
                password = registerUser.password)
        }
    }

}