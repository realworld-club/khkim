package com.example.realworld.application.user.api.dto

import javax.validation.constraints.NotEmpty

data class RequestRegisterUser(
    @field:NotEmpty
    val username: String? = null,

    @field:NotEmpty
    val email: String,

    @field:NotEmpty
    val password: String
)
