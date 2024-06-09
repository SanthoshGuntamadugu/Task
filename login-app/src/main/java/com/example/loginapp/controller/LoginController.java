package com.example.loginapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Login Successful");
        return "success";
    }

    @GetMapping("/loginFailure")
    public String loginFailure(Model model) {
        model.addAttribute("message", "Login Failed");
        return "failure";
    }
}
