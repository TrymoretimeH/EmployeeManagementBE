package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.District;
import com.example.EmployeeManagement.Entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

    @Query("Select w from Ward w where w.districtCode = :code")
    List<Ward> findByDistrictCode(@Param("code") String code);

    @Query("Select w from Ward w where w.code = :code")
    Ward findByCode(@Param("code") String code);
}
