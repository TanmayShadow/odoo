package com.example.odoo.service;

import com.example.odoo.dto.SkillDTO;
import com.example.odoo.model.SkillsModel;
import com.example.odoo.model.UserSkillMappingModel;
import com.example.odoo.repository.SkillRepository;
import com.example.odoo.repository.UserSkillMappingRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillMappingService {
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    UserSkillMappingRepository userSkillMappingRepository;

    public Long addSkill(String skill,Long userId){
        skill = skill.trim().toUpperCase();
        Long skillId = skillRepository.findBySkill(skill).getId();
        if(skillId==null) {
            SkillsModel skillsModel = new SkillsModel();
            skillsModel.setSkill(skill);
            skillId = skillRepository.save(skillsModel).getId();
        }
        try{
            UserSkillMappingModel userSkillMappingModel = new UserSkillMappingModel();
            userSkillMappingModel.setUserId(userId);
            userSkillMappingModel.setSkillId(skillId);
            return userSkillMappingRepository.save(userSkillMappingModel).getId();
        }catch (DataIntegrityViolationException constraintViolationException){
            return null;
        }
    }

    public List<SkillDTO> getAllUserSkills(Long userId) {
        return userSkillMappingRepository.findSkillsByUserid(userId);
    }
}
