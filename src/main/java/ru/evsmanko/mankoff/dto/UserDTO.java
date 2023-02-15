package ru.evsmanko.mankoff.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String city;
}
