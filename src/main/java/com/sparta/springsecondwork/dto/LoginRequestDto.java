package com.sparta.springsecondwork.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginRequestDto {
    private String username;
    private String password;
}