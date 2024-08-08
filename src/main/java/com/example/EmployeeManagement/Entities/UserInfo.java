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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

    public UserInfo(int id, String name, String email, String password, String roles, Employee employee) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.employee = employee;
    }

    public UserInfo(int id, String name, String email, String roles, Employee employee) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.employee = employee;
    }

    public UserInfo(String name, String email, String password, String roles, Employee employee) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.employee = employee;
    }

    public UserInfo() {

    }

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

    public Employee getEmployee() {
        if (employee == null) {
            return new Employee();
        }
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
