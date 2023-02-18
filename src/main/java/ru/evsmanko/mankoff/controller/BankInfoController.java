package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.evsmanko.mankoff.entity.Proposal;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.repository.UserRepository;
import ru.evsmanko.mankoff.service.ArtemServiceImpl;
import ru.evsmanko.mankoff.service.GregoryService;

@Controller
@RequiredArgsConstructor
public class BankInfoController {
    private final GregoryService gregoryService;
    private final UserRepository userRepository;
    private final ArtemServiceImpl artemService;

    @GetMapping("/")
    public String getInfoPage(Model model) {
        model.addAttribute("listUsers", gregoryService.getFirstTwelveUsers());
        model.addAttribute("proposal", new Proposal());
        return "index";
    }

    @PostMapping("/saveProposal")
    public String saveProposal(@ModelAttribute Proposal proposal) {
        gregoryService.saveProposal(proposal);
        return "redirect:/";
    }

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        String time = "Доступ к сайту был получен: " + DateNow.getDate();
        String message = "Прежде чем обращаться куда-либо за чем-либо к кому-либо, убедительная просьба, сверьте нынешнее время с рабочим.";
        model.addAttribute("angryMessage", message);
        model.addAttribute("time", time);
        model.addAttribute("flag", false);
        return "contacts";
    }

    @GetMapping("/information")
    public String getUser(Model model, @RequestParam long id) {
        String time = "Доступ к сайту был получен: " + DateNow.getDate();
        model.addAttribute("time", time);

        User user = userRepository.findUserById(id);

        String message = "Добро пожаловать в хранилище информации. Здесь есть все о наших клиентах. Да, мы за деньги выдаем их персанальные данные. Не вижу в этом ничего такого.";
        model.addAttribute("message", message);
        model.addAttribute("flag", true);
        String information = "User{" +
                "id: " + user.getId() +
                ", Имя: " + user.getFirstName() +
                ", Фамилия: " + user.getLastName() +
                ", Телефон: " + user.getPhone() +
                '}';
        model.addAttribute("information", information);

        return "contacts";
    }
}
