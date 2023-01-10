package ru.evsmanko.mankoff.testController;

import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VeronikaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @DisplayName("Интеграционный тест для контроллера. Ожидаемый рез-тат получен.")
    @Test
    public void testVeronikaControllerCalculateCreditByUserThenStatus200() {
        Double credit = given()
                .spec(new RequestSpecBuilder().setPort(port)
                        .setBasePath("/calculate")
                        .setBaseUri("http://localhost").build())
                .get("/credits/2").then().statusCode(HttpStatus.OK.value())
                .extract().as(Double.class);
        Assertions.assertEquals(credit, 550.0);
    }
}