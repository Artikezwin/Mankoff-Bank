package ru.evsmanko.mankoff.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.evsmanko.mankoff.entity.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setCity(rs.getString("city"));
        user.setPhone(rs.getString("phone"));
        user.setAge(rs.getInt("age"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    };
    private static final String GET_USERS_BY_ID = "SELECT *" +
            " FROM users" +
            " WHERE users.id = :id";

    private static final String GET_ALL_USERS = "SELECT *" +
            " FROM users";

    private static final String SAVE_USER = "INSERT INTO users (id, first_name, last_name, phone, city, age)" +
            " VALUES(:id, :firstName, :lastName, :phone, :city, :age)";

    @Override
    public User findUserById(long id) {
        return jdbcTemplate.queryForObject(GET_USERS_BY_ID,
                new MapSqlParameterSource("id", id),
                rowMapper);
    }

    @Override
    public User save(User user) {
        long id = jdbcTemplate.query(GET_ALL_USERS, rowMapper)
                .stream()
                .map(User::getId)
                .max(Long::compareTo)
                .orElse(1L);
        jdbcTemplate.update(SAVE_USER, new MapSqlParameterSource()
                .addValue("id", ++id)
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("phone", user.getPhone())
                .addValue("city", user.getCity())
                .addValue("age", user.getAge()));
        return findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(GET_ALL_USERS, rowMapper);
    }
}