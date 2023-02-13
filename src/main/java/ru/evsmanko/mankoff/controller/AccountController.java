package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.evsmanko.mankoff.service.VeronikaService;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final VeronikaService veronikaService;

    @GetMapping("/user/{id}")
    public String userInformation(Model model, @PathVariable("id") long id) {
        model.addAttribute("userInformation", veronikaService.getUserInformationById(id));
        return "user-information";
    }

    @GetMapping("/balance")
    public String balanceInformation() {
        return "balance";
    }
}
