package ru.evsmanko.mankoff.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.exception.UserNotFoundException;
import ru.evsmanko.mankoff.repository.UserRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GregoryServiceImpl implements GregoryService {

    private final UserRepository userRepository;

    @Override
    public User exportUserInJson(long id) {
        User user = userRepository.getUserById(id);
        if(user == null) throw  new UserNotFoundException();
        File file = new File("jsons/user" + id + ".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileWriter writer = new FileWriter(file, false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(user));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
