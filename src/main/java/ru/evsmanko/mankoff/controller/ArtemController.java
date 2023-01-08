package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.evsmanko.mankoff.configuration.CurrencyProperties;
import ru.evsmanko.mankoff.service.ArtemServiceImpl;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
@RequestMapping("average")
public class ArtemController {
    private final ArtemServiceImpl artemService;
    private final CurrencyProperties currencyProperties;
    @Value("${currency.RUB}")
    private float defaultCurrency;

    @GetMapping("/currency/rub")
    public ResponseEntity<Float> getAverageIncome(@RequestParam long id) {
        if (artemService != null) {
            return new ResponseEntity<>(artemService.averageIncome(id), HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    private float toUSD(long id) {
        return artemService.averageIncome(id) / defaultCurrency;
    }

    @GetMapping("/currency/usd")
    public ResponseEntity<Float> getAverageUSD(@RequestParam long id) {
        if (artemService != null) {
            return new ResponseEntity<>(toUSD(id), HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/currency/eur")
    public ResponseEntity<Float> getAverageEUR(@RequestParam long id) {
        if (artemService != null) {
            return new ResponseEntity<>(toUSD(id) / currencyProperties.getEUR(), HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

}
