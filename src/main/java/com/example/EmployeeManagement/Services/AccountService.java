package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.Account;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Repositories.AccountRepository;
import com.example.EmployeeManagement.Repositories.EmployeeRepository;
import com.example.EmployeeManagement.Utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Optional<Account> doLogin(String email, String password) {
        String hassPassword = HashUtil.hashPassword(password);
        return accountRepository.doLogin(email, hassPassword);
    }

//    public List<Employee> findByName(String name) {
//        return employeeRepository.findEmployeeByName(name);
//    }

}
