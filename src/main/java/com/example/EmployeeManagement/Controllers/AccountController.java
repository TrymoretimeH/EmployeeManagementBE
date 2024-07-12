package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Entities.Account;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Services.AccountService;
import com.example.EmployeeManagement.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Optional<Account> ExistA = accountService.doLogin(account.getEmail(), account.getPassword());
        if (ExistA.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ExistA.get());
        }
    }


}
