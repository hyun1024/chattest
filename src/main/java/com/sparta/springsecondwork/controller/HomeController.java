package com.sparta.springsecondwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/api/board")
    public String boardTemplates(@RequestParam(value = "username", required = false) String username, Model model){
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "board";
    }
}