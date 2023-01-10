package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evsmanko.mankoff.service.VeronikaServiceImpl;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class VeronikaController {

    private final VeronikaServiceImpl veronikaService;

    @GetMapping("/credits/{id}")
    public ResponseEntity<Double> calculateCreditByUser(@PathVariable("id") long id) {
        if (veronikaService.calculateCreditByUser(id) != null) {
            return new ResponseEntity<>(veronikaService.calculateCreditByUser(id), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}