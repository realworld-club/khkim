package com.example.realworld.application.user.api

import com.example.realworld.application.user.api.dto.RequestRegisterUser
import com.example.realworld.fixture.ApiTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialApiTest : ApiTest() {

    @Test
    fun `회원 추가 dto 테스트`() {
        Given {
            contentType(ContentType.JSON)
            body(RequestRegisterUser(username = "username", email = "email", password = "password"))
        } When {
            post("/api/users")
        } Then {
            statusCode(200)
            body("user.email", equalTo("email"))
        } Extract {
            println(body().jsonPath().prettify())
        }
    }
}
