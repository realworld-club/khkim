package com.realworld.project.fixture;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.realworld.project.application.user.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.ProxySpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.proxy;


public class RestAssuredExtension implements BeforeAllCallback {


    @Override
    public void beforeAll(ExtensionContext context) {
        port = 8081;

        //Fiddler capture
        proxy = ProxySpecification.port(8888);

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
}
