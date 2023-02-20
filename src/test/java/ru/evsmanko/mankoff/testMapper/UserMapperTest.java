package ru.evsmanko.mankoff.testMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.service.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @DisplayName("Тест на работу маппера (из entity в dto), ожидаемый результат получен")
    @Test
    public void testUserToUserDto() {
        User user = new User(
                1L,
                "firstName",
                "lastName",
                "+79999999999",
                20,
                "Moscow"
        );
        UserDTO userDTO = userMapper.userToUserDto(user);
        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getAge(), userDTO.getAge());
        assertEquals(user.getCity(), userDTO.getCity());
        assertEquals(user.getPhone(), userDTO.getPhone());
    }

    @DisplayName("Тест на работу маппера (из dto в entity), ожидаемый результат получен")
    @Test
    public void testUserDtoToUser() {
        UserDTO userDto = new UserDTO(
                1L,
                "firstName",
                "lastName",
                "+79999999999",
                20,
                "Moscow"
        );
        User user = userMapper.userDtoToUser(userDto);
        assertNotNull(user);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getAge(), userDto.getAge());
        assertEquals(user.getCity(), userDto.getCity());
        assertEquals(user.getPhone(), userDto.getPhone());
    }

}
