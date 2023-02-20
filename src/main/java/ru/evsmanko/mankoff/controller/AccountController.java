package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.service.GregoryService;
import ru.evsmanko.mankoff.service.VeronikaService;
import ru.evsmanko.mankoff.service.mapper.UserMapper;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final VeronikaService veronikaService;
    private final GregoryService gregoryService;
    private final UserMapper userMapper;

    @GetMapping("/user/{id}")
    public String userInformation(Model model, @PathVariable("id") long id) {
        UserDTO userDTO = userMapper.userToUserDto(veronikaService.getUserInformationById(id));
        model.addAttribute("userInformation", userDTO);
        return "user-information";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        gregoryService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/balance")
    public String balanceInformation() {
        return "balance";
    }
}
