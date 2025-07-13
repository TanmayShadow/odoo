package com.example.odoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSkillDTO {
    private Long userId;
    private String name;
    private String location;
    private String availability;
    private String photo;
    private Long skillId;
    private String skill;
}
