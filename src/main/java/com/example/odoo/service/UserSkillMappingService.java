package com.example.odoo.service;

import com.example.odoo.dto.SkillDTO;
import com.example.odoo.dto.UserSkillDTO;
import com.example.odoo.dto.UserWithSkillsDTO;
import com.example.odoo.model.SkillsModel;
import com.example.odoo.model.UserSkillMappingModel;
import com.example.odoo.repository.SkillRepository;
import com.example.odoo.repository.UserSkillMappingRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSkillMappingService {
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    UserSkillMappingRepository userSkillMappingRepository;

    public Long addSkill(String skill,Long userId){
        skill = skill.trim().toUpperCase();
        SkillsModel skillsModel1 = skillRepository.findBySkill(skill);
        Long skillId;
        if(skillsModel1==null) {
            SkillsModel skillsModel = new SkillsModel();
            skillsModel.setSkill(skill);
            skillId = skillRepository.save(skillsModel).getId();
        }else{
            skillId = skillsModel1.getId();
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

    public List<UserSkillMappingModel> getAllUsersBySkill(String skill){
        return userSkillMappingRepository.findBySkillName(skill);
    }

//    public List<UserSkillDTO> getPublicUserSkills() {
//        return userSkillMappingRepository.getAllPublicUsersWithSkills();
//    }

    public List<UserWithSkillsDTO> getPublicUserSkills() {
        List<Object[]> flatData = userSkillMappingRepository.getFlatPublicUserSkillData();

        Map<Long, UserWithSkillsDTO> userMap = new LinkedHashMap<>();

        for (Object[] row : flatData) {
            Long userId = ((Number) row[0]).longValue();
            String name = (String) row[1];
            String location = (String) row[2];
            String availability = (String) row[3];
            String photo = (String) row[4];
            Long skillId = ((Number) row[5]).longValue();
            String skill = (String) row[6];

            SkillDTO skillDTO = new SkillDTO(skillId, skill);

            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new UserWithSkillsDTO(userId, name, location, availability, photo, new ArrayList<>()));
            }
            userMap.get(userId).getSkills().add(skillDTO);
        }

        return new ArrayList<>(userMap.values());
    }

}
