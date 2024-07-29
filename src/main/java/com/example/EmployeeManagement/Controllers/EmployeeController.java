package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.DTOs.EmployeeDTO;
import com.example.EmployeeManagement.Entities.Department;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.DepartmentService;
import com.example.EmployeeManagement.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('ADMIN')")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employee) {
        Optional<Employee> existE = employeeService.findByEmail(employee.getEmail());
        if (existE.isPresent()) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Email already exists", employee);
        } else {
            Optional<Department> department = departmentService.getDepartmentById(employee.getDepartmentId());
            if (department.isPresent()) {
                Employee newE = getEmployee(employee, department.get());
                System.out.println("CHECK ADD EMP!");

                System.out.println(newE.getSalaryId());
                employeeService.add(newE);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Create a new employee successfully", newE);
            }

            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found department", employee);
        }
    }

    private static Employee getEmployee(EmployeeDTO employee, Department department) {
        Employee newE = new Employee();
        newE.setDepartment(department);

        newE.setEmail(employee.getEmail());
        newE.setFirstName(employee.getFirstName());
        newE.setLastName(employee.getLastName());
        newE.setAddress(employee.getAddress());
        newE.setPhoneNumber(employee.getPhoneNumber());
        newE.setDateOfBirth(employee.getDateOfBirth());
        newE.setHireDate(employee.getHireDate());
        newE.setPosition(employee.getPosition());
        newE.setSalaryId(employee.getSalaryId());
        return newE;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Optional<Employee> existE = employeeService.getEmployeeById(employee.getId());
        if (existE.isPresent()) {
            employeeService.update(employee);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update a employee successfully", employee);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found employee", employee);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        Optional<Employee> existE = employeeService.getEmployeeById(id);
        if (existE.isPresent()) {
            employeeService.delete(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Employee deleted successfully", null);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found employee", null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchEmployee(@RequestBody String name) {
//        return ResponseEntity.ok().body(employeeService.findByName(name));
        return ResponseEntity.ok().body("Search name: " + name);
    }

}
