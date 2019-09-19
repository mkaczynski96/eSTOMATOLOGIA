package com.estomatologia.estomatologia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/services")
    public String services() {
        return "services/services";
    }

    @GetMapping("/tariff")
    public String tariff() {
        return "tariff/tariff";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact/contact";
    }
}
