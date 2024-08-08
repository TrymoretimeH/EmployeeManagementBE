package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.Ward;
import com.example.EmployeeManagement.Repositories.WardRepository;
import com.example.EmployeeManagement.Repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {

    @Autowired
    WardRepository wardRepository;

    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    public Ward getWardByCode(String code) {
        return wardRepository.findByCode(code);
    }


    public List<Ward> getWardsByDistrictCode(String code) {
        return wardRepository.findByDistrictCode(code);
    }

}
