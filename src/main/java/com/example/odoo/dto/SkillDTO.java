package com.example.odoo.dto;

import lombok.Data;

@Data
public class SkillDTO {
    Long id;
    String skill;
    public SkillDTO(Long id, String skill) {
        this.id = id;
        this.skill = skill;
    }
}
