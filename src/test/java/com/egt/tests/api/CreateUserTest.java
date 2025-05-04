package com.egt.tests.api;

import com.egt.base.api.BaseApiTest;
import com.egt.models.CreateUserApiModel;
import com.egt.utils.FileUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class CreateUserTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("MS-0003 - Create user via API with json template")
    public void createUserByTemplateTest() {
        String requestBody = FileUtils.readFileAsString(baseRequestBodyPath + "createUserRequest.json");
        Allure.step("Submit request for create new user!");
        RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Test User"))
                .body("job", equalTo("Automation Tester"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("MS-0004 - Create user via API with model")
    public void createUserByModelTest() {
        Allure.step("Create user Api Model");
        CreateUserApiModel requestBody = new CreateUserApiModel("Test User", "Automation Tester");
        Allure.step("Submit request for create new user!");
        RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Test User"))
                .body("job", equalTo("Automation Tester"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }
}
