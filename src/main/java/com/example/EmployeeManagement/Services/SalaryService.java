package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Salary;
import com.example.EmployeeManagement.Repositories.DepartmentRepository;
import com.example.EmployeeManagement.Repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    public List<Salary> getAllSalarys() {
        return salaryRepository.findAll();
    }

    public Optional<Salary> getSalaryById(int id) {
        return salaryRepository.findById(id);
    }

    public void add(Salary salary) {
        salaryRepository.save(salary);
    }

    public void update(Salary salary) {
        salaryRepository.save(salary);
    }

    public void delete(int id) {
        salaryRepository.deleteById(id);
    }



}
