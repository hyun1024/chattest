package com.sparta.springsecondwork.controller;

import com.sparta.springsecondwork.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final JwtUtil jwtUtil;

    public PageController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/api/user/login-page")
    public String home() {
        return "login";
    }
    @GetMapping("/api/user/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/api/board")
    public String boardTemplates(@CookieValue(value = "Authorization", required = false) String token, Model model){
        if (token != null) {
            model.addAttribute("username", jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(token)).getSubject());
            return "board";
        } else {
            return "redirect:/api/user/login-page";
        }

    }
}