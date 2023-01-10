package ru.evsmanko.mankoff.testService;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtemRestTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Это тест, который я решил написать по человечески чтобы было красиво")
    void testController() {
        RestAssured.baseURI = "http://localhost";
        given()
                .param("id", "2")
                .spec(new RequestSpecBuilder().setPort(port).build()).
        when()
                .get("/average/currency/rub").
        then()
                .assertThat().body(is(Float.toString(178.0F)))
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест поиска среднего в рублях")
    void testControllerRUB() {
        RestAssured.
                when()
                    .get("http://localhost:" + port + "/average/currency/rub?id=2").
                then()
                    .assertThat().body(is(Float.toString(178.0F)))
                    .statusCode(200);
    }

    @Test
    @DisplayName("Тест поиска среднего в $")
    void testControllerUSD() {
        RestAssured.
                when()
                    .get("http://localhost:" + port + "/average/currency/usd?id=1").
                then()
                    .assertThat().body(is(Float.toString(7777777.0F / 70)))
                    .statusCode(200);
    }

    @Test
    @DisplayName("Тест поиска среднего в евро")
    void testControllerEUR() {
        RestAssured.
                when()
                    .get("http://localhost:" + port + "/average/currency/eur?id=1").
                then()
                    .assertThat().body(is(Float.toString(7777777.0F / 70.0F / 1.1F)))
                    .statusCode(200);
    }
}
