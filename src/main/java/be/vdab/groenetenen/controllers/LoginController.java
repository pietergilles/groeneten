package be.vdab.groenetenen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// enkele imports
@Controller
@RequestMapping("login")
class LoginController {
    @GetMapping
    String login() {
        return "login";
    }
}