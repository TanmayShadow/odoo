package com.example.odoo.repository;

import com.example.odoo.dto.SwapRequestDTO;
import com.example.odoo.model.SwapRequestsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SwapRequestRepository extends JpaRepository<SwapRequestsModel,Long> {
    List<SwapRequestsModel> findByReceiverIdAndStatus(Long userId, String status);

    List<SwapRequestsModel> findByRequesterIdAndStatus(Long userId, String status);

    @Query(value = """
    SELECT new com.example.odoo.dto.SwapRequestDTO(
        sr.id, 
        us.name, 
        ur.name, 
        so.skill, 
        srq.skill,
        sr.message, 
        sr.isAccepted, 
        sr.status, 
        sr.createdAt, 
        sr.updatedAt
    )
    FROM SwapRequestsModel sr
    JOIN UserModel us ON sr.requesterId = us.id
    JOIN UserModel ur ON sr.receiverId = ur.id
    JOIN SkillsModel so ON sr.offeredSkillId = so.id
    JOIN SkillsModel srq ON sr.requestedSkillId = srq.id
    WHERE sr.requesterId = :userId AND sr.status = :status
    """)
    List<SwapRequestDTO> getDetailedSendRequests(@Param("userId") Long userId, @Param("status") String status);


    @Modifying
    @Transactional
    @Query("UPDATE SwapRequestsModel s SET s.status = :status WHERE s.id = :swapRequestId AND s.receiverId = :userId")
    int updateStatusByReceiverIdAndId(Long userId, Long swapRequestId, String status);

    @Modifying
    @Transactional
    @Query("DELETE FROM SwapRequestsModel s WHERE s.id = :swapRequestId AND s.requesterId = :userId")
    int deleteByRequesterIdAndId(Long userId, Long swapRequestId);
}
