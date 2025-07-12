package com.example.odoo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skill")
public class SkillsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String skill;
}
