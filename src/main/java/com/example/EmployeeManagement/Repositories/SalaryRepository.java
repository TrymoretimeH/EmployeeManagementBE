package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
