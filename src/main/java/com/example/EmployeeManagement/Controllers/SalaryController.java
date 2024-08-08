package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Between.UserInfoDetails;
import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Entities.Salary;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salary")
//@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/all")
    public List<Salary> getAllSalary() {

        UserInfoDetails userInfoDetails = (UserInfoDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = userInfoDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        if (isAdmin) {
            return salaryService.getAllSalarys();
        }

        boolean isUser = userInfoDetails.getAuthorities().contains(new SimpleGrantedAuthority("USER"));
        if (isUser) {
            List<Salary> list = new ArrayList<>();
            Optional<Salary> s = salaryService.getSalaryById(userInfoDetails.getEmployee().getSalaryId());
            s.ifPresent(list::add);
            return list;
        }
        return new ArrayList<>();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addSalary(@RequestBody Salary salary) {
        Optional<Salary> existD = salaryService.getSalaryById(salary.getSalaryId());
        if (existD.isPresent()) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Salary already exists", salary);
        } else {
            salaryService.add(salary);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Create a new salary successfully", salary);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<?> updateSalary(@RequestBody Salary salary) {
        Optional<Salary> existD = salaryService.getSalaryById(salary.getSalaryId());
        if (existD.isPresent()) {
            salaryService.update(salary);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update a salary successfully", salary);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found salary", salary);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSalary(@PathVariable int id) {
        Optional<Salary> existD = salaryService.getSalaryById(id);
        if (existD.isPresent()) {
            salaryService.delete(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Salary deleted successfully", null);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found salary", null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchSalary(@RequestBody String name) {
//        return ResponseEntity.ok().body(salaryService.findByName(name));
        return ResponseEntity.ok().body("CHECK SEARCH");
    }

}
