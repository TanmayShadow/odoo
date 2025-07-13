package com.example.odoo.controller;

import com.example.odoo.dto.SkillDTO;
import com.example.odoo.model.UserSkillMappingModel;
import com.example.odoo.service.UserSkillMappingService;
import io.github.tanmayshadow.annotation.ValidateJwtToken;
import io.github.tanmayshadow.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/skills")
public class SkillsController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserSkillMappingService userSkillMappingService;


    @ValidateJwtToken
    @PostMapping("/add")
    public ResponseEntity<?> addUserSkills(@RequestParam("skill") String skill, HttpServletRequest httpServletRequest)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("success",false);
        if(skill==null || skill.isBlank())
        {
            map.put("message","Skill cannot be empty");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        Long userSkillId = userSkillMappingService.addSkill(skill,userId);
        map.put("success",true);
        if(userSkillId==null)
        {
            map.put("message","Skill already exist");
        }
        else{
            map.put("message","Skill added successfully");
            map.put("userSkillId",userSkillId);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ValidateJwtToken
    @GetMapping
    public ResponseEntity<?> getUserSkills(HttpServletRequest httpServletRequest)
    {
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        List<SkillDTO> skills = userSkillMappingService.getAllUserSkills(userId);
        return new ResponseEntity<>(skills,HttpStatus.OK);
    }

//    @ValidateJwtToken
    @GetMapping("/search")
    public ResponseEntity<?> getAllUsersBySkill(@RequestParam("skill")String skill, HttpServletRequest httpServletRequest)
    {
        List<UserSkillMappingModel> skills = userSkillMappingService.getAllUsersBySkill(skill);
        return new ResponseEntity<>(skills,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPublicSkills(){
        return new ResponseEntity<>(userSkillMappingService.getPublicUserSkills(),HttpStatus.OK);
    }
}
