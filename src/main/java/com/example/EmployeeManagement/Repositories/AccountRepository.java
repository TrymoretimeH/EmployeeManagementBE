package com.example.EmployeeManagement.Repositories;

import com.example.EmployeeManagement.Entities.Account;
import com.example.EmployeeManagement.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("Select a from Account a where a.email = :email and a.password = :password")
    Optional<Account> doLogin(@Param("email") String email, @Param("password") String password);



}
