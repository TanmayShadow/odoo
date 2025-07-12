package com.example.odoo.dto;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
