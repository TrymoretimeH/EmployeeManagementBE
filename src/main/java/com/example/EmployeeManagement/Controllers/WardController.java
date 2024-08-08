package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Entities.Ward;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.WardService;
import com.example.EmployeeManagement.Services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ward")
@PreAuthorize("hasAuthority('ADMIN')")
public class WardController {

    @Autowired
    WardService wardService;

    @GetMapping("/all")
    public List<Ward> getAllWards() {
        return wardService.getAllWards();
    }

    @GetMapping("{code}")
    public ResponseEntity<?> getWard(@PathVariable String code) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get ward by code successfully!", wardService.getWardByCode(code));
    }

    @GetMapping("/all/{code}")
    public List<Ward> getWardsByDistrictCode(@PathVariable String code) {
        return wardService.getWardsByDistrictCode(code);
    }

}
