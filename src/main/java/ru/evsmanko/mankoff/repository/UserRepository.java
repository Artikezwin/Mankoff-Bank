package ru.evsmanko.mankoff.repository;

import ru.evsmanko.mankoff.entity.User;

import java.util.List;

public interface UserRepository {
    User getUserById(long id);
    User save(User user);
    List<User> findAll();
}
