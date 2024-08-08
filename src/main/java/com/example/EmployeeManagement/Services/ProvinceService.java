package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.Province;
import com.example.EmployeeManagement.Repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getProvinceByCode(String code) {
        return provinceRepository.findByCode(code);
    }


}
