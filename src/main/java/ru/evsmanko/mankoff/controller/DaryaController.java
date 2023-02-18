package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.evsmanko.mankoff.configuration.CurrencyProperties;
import ru.evsmanko.mankoff.service.DaryaServiceImpl;

@RequiredArgsConstructor
@Controller
@RequestMapping("balance")
public class DaryaController {

    private final DaryaServiceImpl daryaService;
    private final CurrencyProperties currencyProperties;

    private double toUsd() {
        return daryaService.debitAccountsBalance() / currencyProperties.getRUB();
    }

    private double toEur() {
        return toUsd() / currencyProperties.getEUR();
    }

    @GetMapping("/currency/rub")
    public ResponseEntity<Double> debitAccountsBalanceRub() {
        if (daryaService != null) {
            return new ResponseEntity<>(daryaService.debitAccountsBalance(), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/currency/usd")
    public ResponseEntity<Double> debitAccountsBalanceUsd() {
        if (daryaService != null) {
            return new ResponseEntity<>(toUsd(), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/currency/eur")
    public ResponseEntity<Double> debitAccountsBalanceEur() {
        if (daryaService != null) {
            return new ResponseEntity<>(toEur(), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
