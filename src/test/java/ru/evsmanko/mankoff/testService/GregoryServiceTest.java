package ru.evsmanko.mankoff.testService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.service.GregoryServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
public class GregoryServiceTest {

    @Autowired
    private GregoryServiceImpl gregoryService;

    @Value("${app.fileJsonName}")
    private String fileJsonName = "jsons/user.json";

    @DisplayName("Тест на экспорт данных пользователя в json по id, ожидаемый результат получен")
    @Test
    public void testExportUserInJson() {
        long userId = 1;
        User user = new User(userId, "Евгений", "Манько", "79166679083");
        gregoryService.exportUserInJson(userId);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(fileJsonName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                stringBuilder.append(reader.readLine()).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(gson.toJson(user), stringBuilder.toString().trim());
    }
}
