package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Entities.District;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.DistrictService;
import com.example.EmployeeManagement.Services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/district")
@PreAuthorize("hasAuthority('ADMIN')")
public class DistrictController {

    @Autowired
    DistrictService districtService;


    @GetMapping("/all")
    public List<District> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping("{code}")
    public ResponseEntity<?> getDistrict(@PathVariable String code) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get district by code successfully!", districtService.getDistrictByCode(code));
    }

    @GetMapping("/all/{code}")
    public List<District> getDistrictsByProvinceCode(@PathVariable String code) {
        return districtService.getDistrictsByProvinceCode(code);
    }

}
