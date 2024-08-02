package com.example.EmployeeManagement.Controllers;

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
@RequestMapping("/api/department")
//@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('ADMIN')")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        Optional<Department> existD = departmentService.getDepartmentById(department.getDepartmentId());
        if (existD.isPresent()) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Department already exists", department);
        } else {
            departmentService.add(department);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Create a new department successfully", department);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDep(@RequestBody Department department) {
        Optional<Department> existD = departmentService.getDepartmentById(department.getDepartmentId());
        if (existD.isPresent()) {
            departmentService.update(department);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update a department successfully", department);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found department", department);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDep(@PathVariable int id) {
        Optional<Department> existD = departmentService.getDepartmentById(id);
        if (existD.isPresent()) {
            departmentService.delete(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Department deleted successfully", null);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found department", null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchDep(@RequestBody String name) {
//        return ResponseEntity.ok().body(departmentService.findByName(name));
        return ResponseEntity.ok().body("CHECK SEARCH");
    }

}
