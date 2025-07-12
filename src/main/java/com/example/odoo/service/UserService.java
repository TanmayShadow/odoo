package com.example.odoo.service;

import com.example.odoo.dto.LoginDTO;
import com.example.odoo.model.UserModel;
import com.example.odoo.repository.UserRepository;
import io.github.tanmayshadow.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;
    int tokenExpirationTime = 172800000;//1000*60*60*48 = 2 Days

    private String generateEncryptedPassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean isEmailValid(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public String register(UserModel userModel){
        if(!this.isEmailValid(userModel.getEmail()))
            return "Email is not valid";
        System.out.println(userModel.getPassword());
        userModel.setPassword(generateEncryptedPassword(userModel.getPassword()));
        try {
            userRepository.save(userModel);
        }catch (DataIntegrityViolationException exception){
            return "Email already exist";
        }catch (Exception exception){
            return "User Addition failed";
        }
        return "SUCCESS";
    }

    public String login(LoginDTO loginDTO){
        try {
            if(!this.isEmailValid(loginDTO.getEmail()))
                return null;
            UserModel  searchedUser = userRepository.findByEmail(loginDTO.getEmail());
            if(passwordEncoder.matches(loginDTO.getPassword(),searchedUser.getPassword()))
            {
                Map<String, Object> map = new HashMap<>();
                map.put("user",searchedUser.getId());
                map.put("email",searchedUser.getEmail());
                map.put("name",searchedUser.getName());
                map.put("location",searchedUser.getLocation());
                map.put("availability",searchedUser.getAvailability());
                map.put("isPublic",searchedUser.getIsPublic());
                return jwtUtil.generateJwtToken(map,this.tokenExpirationTime);
            }
        }catch (NullPointerException nullPointerException){
            return null;
        }
        return null;
    }
}
