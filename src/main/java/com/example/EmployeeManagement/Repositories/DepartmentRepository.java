package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("select d from Department d where d.managerId = :m")
    List<Department> getDepartmentsByManagerId(@Param("m") int m);

}
