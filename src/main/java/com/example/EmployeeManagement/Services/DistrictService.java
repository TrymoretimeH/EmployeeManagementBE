package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.District;
import com.example.EmployeeManagement.Entities.Province;
import com.example.EmployeeManagement.Repositories.DistrictRepository;
import com.example.EmployeeManagement.Repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictByCode(String code) {
        return districtRepository.findByCode(code);
    }


    public List<District> getDistrictsByProvinceCode(String code) {
        return districtRepository.findByProvinceCode(code);
    }

}
