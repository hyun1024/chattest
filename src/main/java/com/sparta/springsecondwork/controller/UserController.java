package com.sparta.springsecondwork.controller;

import com.sparta.springsecondwork.dto.LoginRequestDto;
import com.sparta.springsecondwork.dto.SignupRequestDto;
import com.sparta.springsecondwork.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error";
        }

        return "redirect:/api/board";
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NotFoundExceptionHandler(NullPointerException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}