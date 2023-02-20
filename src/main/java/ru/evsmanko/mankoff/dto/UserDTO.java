package ru.evsmanko.mankoff.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String city;
}
