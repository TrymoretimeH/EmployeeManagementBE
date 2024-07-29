package com.example.EmployeeManagement.Between;

import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
    private int id;
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
    private Employee employee;

    public UserInfoDetails(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.name = userInfo.getEmail();
        this.password = userInfo.getPassword();
        this.authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.employee = userInfo.getEmployee();
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
