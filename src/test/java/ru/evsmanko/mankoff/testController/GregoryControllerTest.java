package ru.evsmanko.mankoff.testController;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GregoryControllerTest {

    @DisplayName("Тест на работу контроллера, ожидаемый результат получен")
    @Test
    public void testGregoryControllerReturn200WithExpectedUser() {
        when()
                .get("/gregory/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1),
                        "firstName", equalTo("Евгений"),
                        "lastName", equalTo("Манько"),
                        "phone", equalTo("79166679083"));
    }
}
