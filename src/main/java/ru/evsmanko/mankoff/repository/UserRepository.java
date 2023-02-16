package ru.evsmanko.mankoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.evsmanko.mankoff.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String findAll = "SELECT * FROM USERS";
    String findUserById = "SELECT * FROM USERS WHERE id = :given_id";
    String saveUser = "INSERT INTO users(first_name, last_name, phone, city, age) \" +\n" +
            "            \"VALUES (:firstName, :lastName, :phone,\" +\n" +
            "            \" :city, :age)";

    @Query(nativeQuery = true, value = findUserById)
    User getUserById(@Param("given_id") long id);

    @Modifying
    @Query(value = saveUser, nativeQuery = true)
    User save(@Param("firstName") String firstName,
              @Param("lastName") String lastName,
              @Param("phone") String phone,
              @Param("city") String city,
              @Param("age") Integer age
    );

    @Query(nativeQuery = true, value = findAll)
    List<User> findAll();
}
