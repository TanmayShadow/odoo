package com.example.odoo.service;

import com.example.odoo.dto.SwapRequestDTO;
import com.example.odoo.model.SwapRequestsModel;
import com.example.odoo.repository.SwapRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwapRequestService {
    @Autowired
    SwapRequestRepository swapRequestRepository;
    public Long sendRequest(SwapRequestsModel swapRequestsModel) {
        try{
            return swapRequestRepository.save(swapRequestsModel).getId();
        }catch(DataIntegrityViolationException constraintViolationException){
            return null;
        }
    }

    public List<SwapRequestsModel> getSwapRequests(Long userId, String status) {
        status = status.trim().toUpperCase();
        return swapRequestRepository.findByReceiverIdAndStatus(userId,status);
    }

    public List<SwapRequestsModel> getSendSwapRequests(Long userId, String status) {
        status = status.trim().toUpperCase();
        return swapRequestRepository.findByRequesterIdAndStatus(userId,status);
    }
//    public List<SwapRequestDTO> getSendSwapRequests(Long userId, String status) {
//        status = status.trim().toUpperCase();
//        return swapRequestRepository.getDetailedSendRequests(userId, status);
//    }

    public String updateSwapRequestStatus(Long userId, Long swapRequestId, String status) {
        status = status.trim().toUpperCase();

        int updatedRows = swapRequestRepository.updateStatusByReceiverIdAndId(userId, swapRequestId, status);

        if (updatedRows > 0) {
            return "Status updated successfully";
        } else {
            return "Swap request not found or user not authorized";
        }
    }

    public String deleteSendRequest(Long userId, Long swapRequestId) {
        int deletedRows = swapRequestRepository.deleteByRequesterIdAndId(userId, swapRequestId);

        if (deletedRows > 0) {
            return "Swap request deleted successfully.";
        } else {
            return "Swap request not found or user not authorized to delete it.";
        }
    }
}
