package com.example.realworld.application.user.api

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialApiTest() {

    @Test
    fun registrationApi() {
        Given {
            contentType(ContentType.JSON)
            body(RequestRegisterUser(username = "username", email = "email", password = "password"))
        } When {
            post("/api/users")
        } Then {
            statusCode(200)
        }
    }
}
