package ru.evsmanko.mankoff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.evsmanko.mankoff.service.ArtemServiceImpl;

@RequiredArgsConstructor
@Controller
@RequestMapping("average")
public class ArtemController {
    private final ArtemServiceImpl artemService;

    @GetMapping
    public ResponseEntity<Float> getAverageIncome(@RequestParam long id) {
        if (artemService != null) {
            return new ResponseEntity<>(artemService.averageIncome(id), HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }
}
