package com.example.realworld.fixture

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.web.server.LocalServerPort
import java.lang.reflect.Type

open class ApiTest {
    @LocalServerPort var port: Int = 0

    @BeforeEach
    fun beforeEach() {
        RestAssured.port = port
        //serialize, deserialize
        RestAssured.config = RestAssuredConfig.config()
            .objectMapperConfig(
                ObjectMapperConfig().jackson2ObjectMapperFactory { cls: Type?, charset: String? ->
                    val mapper =
                        ObjectMapper().findAndRegisterModules()
                    mapper.enable(SerializationFeature.WRAP_ROOT_VALUE)
                    mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE)
                    mapper
                }
            )
    }
}