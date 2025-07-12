package com.example.odoo.controller;

import com.example.odoo.dto.LoginDTO;
import com.example.odoo.model.UserModel;
import com.example.odoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel userModel){
        String status = userService.register(userModel);
        Map<String,Object> response = new HashMap<>();
        if(status.equals("SUCCESS")){
            response.put("isSuccess",true);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.put("isSuccess",false);
        response.put("message",status);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        String token = userService.login(loginDTO);
        Map<String,Object> response = new HashMap<>();
        if(token==null){
            response.put("isSuccess",false);
            response.put("message","Invalid Email or Password");
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
        }
        response.put("isSuccess",true);
        response.put("token",token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
