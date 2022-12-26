package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.evsmanko.mankoff.entity.User;
import ru.evsmanko.mankoff.service.GregoryService;

@RestController
@RequestMapping("gregory")
@RequiredArgsConstructor
public class GregoryController {

    private final GregoryService gregoryService;

    @GetMapping("/{id}")
    public ResponseEntity<User> exportUserInJson(@PathVariable("id") long id) {
        User user = gregoryService.exportUserInJson(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
