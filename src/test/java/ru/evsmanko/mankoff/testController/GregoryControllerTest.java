package ru.evsmanko.mankoff.testController;

import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import ru.evsmanko.mankoff.entity.User;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GregoryControllerTest {

    @LocalServerPort
    private int port;

    @DisplayName("Тест на работу контроллера, ожидаемый результат получен")
    @Test
    public void testGregoryControllerReturn200WithExpectedUser() {
        User user = given()
                .spec(new RequestSpecBuilder().setPort(port)
                        .setBasePath("/gregory")
                        .setBaseUri("http://localhost").build())
                .get("/1").then().statusCode(HttpStatus.OK.value())
                .extract().as(User.class);

        assertEquals(user.getFirstName(), "Евгений");
        assertEquals(user.getLastName(), "Манько");
        assertEquals(user.getPhone(), "79166679083");
    }
}
