package com.example.realworld.application.user.api.dto

import com.fasterxml.jackson.annotation.JsonRootName
import javax.validation.constraints.NotEmpty

@JsonRootName("user")
data class RequestRegisterUser(
    @field:NotEmpty
    val username: String,

    @field:NotEmpty
    val email: String,

    //for test
    @field:NotEmpty
    val password: String?= null
)
