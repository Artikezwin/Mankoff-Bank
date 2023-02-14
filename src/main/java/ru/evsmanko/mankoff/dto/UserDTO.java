package ru.evsmanko.mankoff.dto;

import lombok.Data;
import ru.evsmanko.mankoff.entity.User;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String city;

    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.age = user.getAge();
        this.city = user.getCity();
    }
}
