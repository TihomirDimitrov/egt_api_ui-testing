package com.egt.base.api;

import com.egt.configuration.ApiTestConfig;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

@Slf4j
@SpringBootTest
public abstract class BaseApiTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApiTestConfig config;

    protected String baseUrl;
    protected String baseRequestBodyPath;

    @BeforeClass
    public void setUp() {
        this.baseUrl = config.getBaseUrl();
        this.baseRequestBodyPath = config.getRequestBodyPath();

        // Настройваме RestAssured
        RestAssured.baseURI = baseUrl;

        log.info("API Config loaded from Spring profile:");
        log.info("baseUrl = {}", baseUrl);
        log.info("requestBodyPath = {}", baseRequestBodyPath);
    }
}
