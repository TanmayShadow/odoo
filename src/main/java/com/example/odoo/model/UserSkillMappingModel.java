package com.example.odoo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "UserSkillMapping", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "skillId"}))
public class UserSkillMappingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long skillId;
    private String type;
}
