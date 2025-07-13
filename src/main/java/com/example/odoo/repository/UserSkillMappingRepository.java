package com.example.odoo.repository;

import com.example.odoo.dto.SkillDTO;
import com.example.odoo.dto.UserSkillDTO;
import com.example.odoo.model.UserSkillMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSkillMappingRepository extends JpaRepository<UserSkillMappingModel,Long> {
    @Query(value = "select user_skill_mapping.id as id,skill.skill as skill " +
            "from user_skill_mapping " +
            "join skill " +
            "on user_skill_mapping.skill_id = skill.id " +
            "where user_skill_mapping.user_id=?1;", nativeQuery = true)
    List<SkillDTO> findSkillsByUserid(Long userId);

    @Query(value = "SELECT usm.id, usm.user_id, usm.skill_id, usm.type " +
            "FROM user_skill_mapping usm " +
            "JOIN skill s ON usm.skill_id = s.id " +
            "JOIN user u ON usm.user_id = u.id " +
            "WHERE s.skill = ?1 AND u.is_public = TRUE", nativeQuery = true)
    List<UserSkillMappingModel> findBySkillName(String skill);

    @Query("SELECT new com.example.odoo.dto.UserSkillDTO(u.id, u.name, u.location, u.availability, u.photo, s.id, s.skill) " +
            "FROM UserModel u " +
            "JOIN UserSkillMappingModel usm ON u.id = usm.userId " +
            "JOIN SkillsModel s ON s.id = usm.skillId " +
            "WHERE u.isPublic = true")
    List<UserSkillDTO> getAllPublicUsersWithSkills();

    @Query(value = "SELECT u.id as userId, u.name, u.location, u.availability, u.photo, s.id as skillId, s.skill " +
            "FROM user u " +
            "JOIN user_skill_mapping usm ON u.id = usm.user_id " +
            "JOIN skill s ON s.id = usm.skill_id " +
            "WHERE u.is_public = true", nativeQuery = true)
    List<Object[]> getFlatPublicUserSkillData();
}
