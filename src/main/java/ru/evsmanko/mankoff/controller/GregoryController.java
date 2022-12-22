package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.service.GregoryService;

@RestController
@RequestMapping("gregory")
@RequiredArgsConstructor
public class GregoryController {

    private final GregoryService gregoryService;

    @GetMapping
    public User exportUserInJson(@RequestParam long id) {
        return gregoryService.exportUserInJson(id);
    }
}
