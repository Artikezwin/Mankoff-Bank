package ru.evsmanko.mankoff.service.mapper;

import org.springframework.stereotype.Service;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.User;

@Service
public class MappingUtils {
    public UserDTO mapToUserDto (User user) {
        UserDTO userDTO = new UserDTO();
        if(user.getId() != null) {
            userDTO.setId(user.getId());
        }
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setCity(user.getCity());
        userDTO.setAge(user.getAge());
        return userDTO;
    }

    public User mapToUserEntity(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setPhone(userDTO.getPhone());
        user.setAge(userDTO.getAge());
        return user;
    }
}
