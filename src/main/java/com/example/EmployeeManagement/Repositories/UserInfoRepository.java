package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByEmail(String email);

    @Query("Select u from UserInfo u where u.employee = :e")
    UserInfo findByEmployee(@Param("e") Employee e);
}
