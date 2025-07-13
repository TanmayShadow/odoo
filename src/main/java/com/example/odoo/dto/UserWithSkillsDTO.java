package com.example.odoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserWithSkillsDTO {
    private Long userId;
    private String name;
    private String location;
    private String availability;
    private String photo;
    private List<SkillDTO> skills;
}