package com.estomatologia.estomatologia.controller;

import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/logme")
    public String showSmth() {
        return "index";
    }
}
