package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.evsmanko.mankoff.entity.Proposal;
import ru.evsmanko.mankoff.service.GregoryService;

@Controller
@RequiredArgsConstructor
public class BankInfoController {
    private final GregoryService gregoryService;

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
    public String getContacts(){
        return "contacts";
    }
}
