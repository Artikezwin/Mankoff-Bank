package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.evsmanko.mankoff.service.VeronikaServiceImpl;

@RequiredArgsConstructor
@RestController
@RequestMapping("calculate")
public class VeronikaController {

    private final VeronikaServiceImpl veronikaService;

    @GetMapping("/credits")
    public ResponseEntity<Double> calculateCreditByUser(@RequestParam long id) {
        if (veronikaService != null) {
            return new ResponseEntity<>(veronikaService.calculateCreditByUser(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}