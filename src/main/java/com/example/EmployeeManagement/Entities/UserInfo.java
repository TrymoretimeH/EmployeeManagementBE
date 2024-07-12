package com.example.EmployeeManagement.Entities;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "USERINFO")
public class UserInfo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String roles;

    @Nullable
    @Column(name = "EMP_ID")
    private Integer empId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getEmpId() {
        if (empId == null) {
            return 0;
        }
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
