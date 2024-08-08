package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    @Query("Select p from Province p where p.code = :code")
    Optional<Province> findByCode(@Param("code") String code);
}
