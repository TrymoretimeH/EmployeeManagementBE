package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Attendance;
import com.example.EmployeeManagement.Entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
