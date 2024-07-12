package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Repositories.DepartmentRepository;
import com.example.EmployeeManagement.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    public void add(Department department) {
        departmentRepository.save(department);
    }

    public void update(Department department) {
        departmentRepository.save(department);
    }

    public void delete(int id) {
        departmentRepository.deleteById(id);
    }



}
