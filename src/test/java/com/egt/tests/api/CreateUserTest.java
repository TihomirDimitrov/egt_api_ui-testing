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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class CreateUserTest extends BaseApiTest {

    @Test(dataProvider = "userBodyProvider")
    @Severity(SeverityLevel.BLOCKER)
    @Story("MS-0003 - Create user via file or model")
    public void testCreateUserWithVariousSources(Object requestBody, ContentType contentType, String sourceType) {
        Allure.step("Submit request using body from: " + sourceType);

        RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(contentType)
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

    @DataProvider(name = "userBodyProvider", parallel = true)
    public Object[][] userBodyProvider() {
        String fileBody = FileUtils.readFileAsString("src/test/resources/requests/createUserRequest.json");

        CreateUserApiModel modelBody = new CreateUserApiModel("Test User", "Automation Tester");

        return new Object[][]{
                {fileBody, ContentType.JSON, "json"},
                {modelBody, ContentType.JSON, "model"}
        };
    }
}
