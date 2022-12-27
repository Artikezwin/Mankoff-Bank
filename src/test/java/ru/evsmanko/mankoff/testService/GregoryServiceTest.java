package ru.evsmanko.mankoff.testService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import ru.evsmanko.mankoff.configuration.AppProperties;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.UserRepository;
import ru.evsmanko.mankoff.service.GregoryServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class GregoryServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private AppProperties appProperties;
    @InjectMocks
    private GregoryServiceImpl gregoryService;

    @Value("${app.fileJsonName}")
    private String fileJsonName = "jsons/user.json";

    @DisplayName("Тест на экспорт данных пользователя в json по id, ожидаемый результат получен")
    @Test
    public void testExportUserInJson() {
        long userId = 1;
        User user = new User(userId, "firstName", "lastName", "123345");
        when(appProperties.getFileJsonName()).thenReturn(fileJsonName);
        when(userRepository.getUserById(userId)).thenReturn(user);
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
