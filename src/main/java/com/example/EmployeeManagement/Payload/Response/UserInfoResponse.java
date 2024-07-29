package com.example.EmployeeManagement.Payload.Response;

import com.example.EmployeeManagement.Entities.Employee;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserInfoResponse {
    private int id;
    private String name;
    private Collection<? extends GrantedAuthority> roles;
    private Employee employee;

    public UserInfoResponse() {}

//    public UserInfoResponse(int id, String name, Employee employee, List<String> roles) {
//
//    }

    public UserInfoResponse(int id, String username, Employee employee, Collection<? extends GrantedAuthority> authorities) {
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

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
