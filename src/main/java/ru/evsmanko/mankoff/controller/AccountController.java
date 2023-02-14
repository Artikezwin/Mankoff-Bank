package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.evsmanko.mankoff.dto.UserDTO;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.service.GregoryService;
import ru.evsmanko.mankoff.service.VeronikaService;
import ru.evsmanko.mankoff.service.mapper.MappingUtils;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final VeronikaService veronikaService;
    private final GregoryService gregoryService;
    private final MappingUtils mappingUtils;

    @GetMapping("/user/{id}")
    public String userInformation(Model model, @PathVariable("id") long id) {
        UserDTO userDTO = mappingUtils.mapToUserDto(veronikaService.getUserInformationById(id));
        model.addAttribute("userInformation", userDTO);
        return "user-information";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDTO userDTO) {
        User user = mappingUtils.mapToUserEntity(userDTO);
        gregoryService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/balance")
    public String balanceInformation() {
        return "balance";
    }
}
