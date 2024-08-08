package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Between.UserInfoDetails;
import com.example.EmployeeManagement.Entities.Province;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/province")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;


    @GetMapping("/all")
    public List<Province> getAllProvinces() {
        return provinceService.getAllProvinces();
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getProvinceByCode(@PathVariable String code) {
        Optional<Province> province = provinceService.getProvinceByCode(code);
        return province.map(value -> ResponseHandler.generateResponse(HttpStatus.OK, true, "Get province by id successfully!", value)).orElseGet(() -> ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Not found province!", null));
    }

}
