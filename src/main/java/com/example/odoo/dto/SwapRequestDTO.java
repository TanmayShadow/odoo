package com.example.odoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwapRequestDTO {
    private Long id;
    private String requesterName;
    private String receiverName;
    private String offeredSkill;
    private String requestedSkill;
    private Long requesterId;
    private Long receiverId;
    private Long offeredId;
    private Long requestedId;
    private String message;
    private boolean isAccepted;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Add constructor, getters, setters
}
