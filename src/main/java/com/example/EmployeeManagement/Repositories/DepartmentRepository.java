package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
