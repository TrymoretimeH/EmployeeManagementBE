package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Attendance;
import com.example.EmployeeManagement.Entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    @Query("select a from Attendance a where a.employee.id = :id")
    List<Attendance> findByEmployeeId(@Param("id") int id);
}
