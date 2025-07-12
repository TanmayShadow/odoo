package com.example.odoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "swap_request",uniqueConstraints = @UniqueConstraint(columnNames = {"requesterId", "receiverId","offeredSkillId","requestedSkillId"}))
public class SwapRequestsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Long requesterId;
    Long receiverId;
    Long offeredSkillId;
    Long requestedSkillId;
    String message;
    Boolean isAccepted;
    String status;
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(updatable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
