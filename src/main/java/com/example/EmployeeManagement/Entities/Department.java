package com.example.EmployeeManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID")
    private int departmentId;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employeeList;

    @Column(name = "MANAGER_ID")
    private Integer managerId;

    public Department() {
    }

    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    public Department(String departmentName, String description, int managerId) {
        this.departmentName = departmentName;
        this.description = description;
        this.managerId = managerId;
    }

    public Department(String departmentName, String description, List<Employee> employeeList) {
        this.departmentName = departmentName;
        this.description = description;
        this.employeeList = employeeList;
    }



    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public int getManagerId() {
        if (managerId == null)
                return 0;
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
