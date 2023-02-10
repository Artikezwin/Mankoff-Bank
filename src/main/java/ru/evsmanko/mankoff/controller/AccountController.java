package ru.evsmanko.mankoff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

        @GetMapping("/user")
        public String userInformation() {
            return "user-information";
        }
    }
