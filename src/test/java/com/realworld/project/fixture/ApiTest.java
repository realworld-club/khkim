package com.realworld.project.fixture;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.realworld.project.application.user.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.proxy;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiTest {

    public static Response response;

    @Autowired
    private UserRepository userRepository;

    @Value("${server.port}")
    public void setKey(int value) {
        port = value;
    }

    @BeforeAll
    static void beforeAll() {
        //serialize, deserialize
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(
                        new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> {
                            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
                            mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
                            mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
                            return mapper;
                        })
                );
    }

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }
}
