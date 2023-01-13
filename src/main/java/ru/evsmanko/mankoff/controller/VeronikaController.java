package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evsmanko.mankoff.configuration.CurrencyProperties;
import ru.evsmanko.mankoff.service.VeronikaServiceImpl;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class VeronikaController {

    private final VeronikaServiceImpl veronikaService;
    private final CurrencyProperties currencyProperties;

    @GetMapping("/credits/rub/{id}")
    public ResponseEntity<Double> calculateCreditByUserRub(@PathVariable("id") long id) {
        if (veronikaService.calculateCreditByUser(id) != null) {
            return new ResponseEntity<>(veronikaService.calculateCreditByUser(id), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/credits/usd/{id}")
    public ResponseEntity<Double> calculateCreditByUserUsd(@PathVariable("id") long id) {
        Double credits = veronikaService.calculateCreditByUser(id);
        if (credits != null) {
            Double creditsUsd = Math.round(credits / currencyProperties.getRUB() * 100.0) / 100.0;
            return new ResponseEntity<>(creditsUsd, HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/credits/eur/{id}")
    public ResponseEntity<Double> calculateCreditByUserEur(@PathVariable("id") long id) {
        Double credits = veronikaService.calculateCreditByUser(id);
        if (credits != null) {
            Double creditsUsd = credits / currencyProperties.getRUB();
            Double creditsEur = Math.round(creditsUsd / currencyProperties.getEUR() * 100.0) / 100.0;
            return new ResponseEntity<>(creditsEur, HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}