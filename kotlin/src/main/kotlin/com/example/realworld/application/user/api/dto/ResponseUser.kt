package com.example.realworld.application.user.api.dto

import com.example.realworld.application.user.domain.User
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("user")
class ResponseUser(
    var email: String,
    var token: String?= null,
    var username: String?= null,
    var bio: String?= null,
    var image: String?= null) {

    companion object {
        fun from(user: User) : ResponseUser {
            return ResponseUser(
                email = user.email,
                token = null,
                username = null,
                bio = null,
                image = null)
        }
    }
}
