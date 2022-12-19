package ru.evsmanko.mankoff.testService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.UserRepository;
import ru.evsmanko.mankoff.service.GregoryService;
import ru.evsmanko.mankoff.service.GregoryServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GregoryServiceTest {

    private UserRepository userRepository;
    private GregoryService gregoryService;

    @DisplayName("Тест на экспорт данных пользователя в json по id, ожидаемый результат получен")
    @Test
    public void testExportUserInJson() {
        userRepository = Mockito.mock(UserRepository.class);
        gregoryService = new GregoryServiceImpl(userRepository);
        long userId = 1;
        User user = new User(userId, "firstName", "lastName", "123345");
        Mockito.when(userRepository.getUserById(userId)).thenReturn(user);
        gregoryService.exportUserInJson(userId);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("jsons/user" + userId + ".json");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()){
                stringBuilder.append(reader.readLine()).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(gson.toJson(user), stringBuilder.toString().trim());
    }
}
