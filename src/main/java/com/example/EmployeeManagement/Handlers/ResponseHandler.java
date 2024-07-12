package com.example.EmployeeManagement.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean success, String message, Object responseObje) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            responseMap.put("status", status.value());
            responseMap.put("isSuccess", success);
            responseMap.put("message", message);
            responseMap.put("data", responseObje);
            return new ResponseEntity<Object>(responseMap, status);
        } catch (Exception e) {
            responseMap.clear();
            responseMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseMap.put("isSuccess", false);
            responseMap.put("message", e.getMessage());
            responseMap.put("data", null);
            return new ResponseEntity<Object>(responseMap, status);

        }
    }
}
