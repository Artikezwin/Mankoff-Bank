package ru.evsmanko.mankoff.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evsmanko.mankoff.configuration.AppProperties;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.Proposal;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.exception.UserNotFoundException;
import ru.evsmanko.mankoff.repository.ProposalRepository;
import ru.evsmanko.mankoff.repository.UserRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GregoryServiceImpl implements GregoryService {

    private final UserRepository userRepository;
    private final AppProperties appProperties;
    private final ProposalRepository proposalRepository;

    @Override
    public User exportUserInJson(long id) {
        User user = userRepository.getUserById(id);
        if (user == null) throw new UserNotFoundException();
        File file = new File(appProperties.getFileJsonName());
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

    @Override
    public List<User> getFirstTwelveUsers() {
        return userRepository.findAll().stream().limit(12).toList();
    }

    @Override
    public Proposal saveProposal(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getCity(),
                user.getAge()
        );
    }
}
