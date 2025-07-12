package com.example.odoo.repository;

import com.example.odoo.model.SkillsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<SkillsModel,Long> {
    SkillsModel findBySkill(String skill);
}
