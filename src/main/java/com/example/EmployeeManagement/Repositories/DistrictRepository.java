package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.District;
import com.example.EmployeeManagement.Entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    @Query("Select d from District d where d.provinceCode = :code")
    List<District> findByProvinceCode(@Param("code") String code);

    @Query("Select d from District d where d.code = :code")
    District findByCode(@Param("code") String code);
}
