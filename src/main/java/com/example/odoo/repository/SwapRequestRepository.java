package com.example.odoo.repository;

import com.example.odoo.model.SwapRequestsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SwapRequestRepository extends JpaRepository<SwapRequestsModel,Long> {
    List<SwapRequestsModel> findByReceiverIdAndStatus(Long userId, String status);

    List<SwapRequestsModel> findByRequesterIdAndStatus(Long userId, String status);

    @Modifying
    @Transactional
    @Query("UPDATE SwapRequestsModel s SET s.status = :status WHERE s.id = :swapRequestId AND s.receiverId = :userId")
    int updateStatusByReceiverIdAndId(Long userId, Long swapRequestId, String status);

    @Modifying
    @Transactional
    @Query("DELETE FROM SwapRequestsModel s WHERE s.id = :swapRequestId AND s.requesterId = :userId")
    int deleteByRequesterIdAndId(Long userId, Long swapRequestId);
}
