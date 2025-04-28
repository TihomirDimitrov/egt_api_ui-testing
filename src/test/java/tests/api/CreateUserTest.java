package tests.api;

import base.api.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import models.CreateUserRequest;
import org.testng.annotations.Test;
import utils.FileUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class CreateUserTest extends BaseApiTest {

    @Test
    public void createUserByTemplateTest() {
        String requestBody = FileUtils.readFileAsString(baseRequestBodyPath + "createUserRequest.json");

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
    public void createUserByModelTest() {
        CreateUserRequest requestBody = new CreateUserRequest("Test User", "Automation Tester");

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
