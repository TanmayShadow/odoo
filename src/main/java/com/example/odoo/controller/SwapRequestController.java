package com.example.odoo.controller;

import com.example.odoo.model.SwapRequestsModel;
import com.example.odoo.service.SwapRequestService;
import io.github.tanmayshadow.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/swapRequest")
public class SwapRequestController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    SwapRequestService swapRequestService;

    @PostMapping("/sendRequest")
    public ResponseEntity<?> sendSwapRequest(@RequestBody SwapRequestsModel swapRequestsModel, HttpServletRequest httpServletRequest){
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        swapRequestsModel.setRequesterId(userId);
        swapRequestsModel.setIsAccepted(false);
        swapRequestsModel.setStatus("PENDING");
        Long sendRequestId = swapRequestService.sendRequest(swapRequestsModel);
        Map<String,Object> map =new HashMap<>();
        map.put("swapRequestId",sendRequestId);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/receivedRequest")
    public ResponseEntity<?> getMySwapRequest(@RequestParam("status") String status,HttpServletRequest httpServletRequest){
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        List<SwapRequestsModel> swapRequestsModelList = swapRequestService.getSwapRequests(userId,status);
        return new ResponseEntity<>(swapRequestsModelList,HttpStatus.OK);
    }

    @GetMapping("/sendRequest")
    public ResponseEntity<?> getMySendSwapRequest(@RequestParam("status") String status,HttpServletRequest httpServletRequest){
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        List<SwapRequestsModel> swapRequestsModelList = swapRequestService.getSendSwapRequests(userId,status);
        return new ResponseEntity<>(swapRequestsModelList,HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<?> updateRequestStatus(@RequestParam("swapRequestId") Long swapRequestId,@RequestParam("status") String status,HttpServletRequest httpServletRequest){
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        String response = swapRequestService.updateSwapRequestStatus(userId,swapRequestId,status);
        Map<String,Object> map = new HashMap<>();
        map.put("message",response);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/deleteRequest")
    public ResponseEntity<?> deleteSendRequest(@RequestParam("swapRequestId") Long swapRequestId,HttpServletRequest httpServletRequest){
        Long userId = Long.parseLong(jwtUtil.getClaim(httpServletRequest,"user")+"");
        String response = swapRequestService.deleteSendRequest(userId,swapRequestId);
        Map<String,Object> map = new HashMap<>();
        map.put("message",response);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
