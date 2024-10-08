package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("Select e from Employee e where e.email = :email")
    Optional<Employee> findByEmail(@Param("email") String email);


}
