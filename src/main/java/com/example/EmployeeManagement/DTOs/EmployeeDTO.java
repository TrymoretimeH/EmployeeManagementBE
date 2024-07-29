package com.example.EmployeeManagement.DTOs;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmployeeDTO {
    private int id;
    private String address;
    private Date dateOfBirth;
    private int departmentId;
    private String email;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String phoneNumber;
    private String position;
    private int salaryId;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String address, Date dateOfBirth, int departmentId, String email, String firstName, String lastName, Date hireDate, String phoneNumber, String position, int salaryId) {
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.departmentId = departmentId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.salaryId = salaryId;
    }

    public EmployeeDTO(int id, String address, Date dateOfBirth, int departmentId, String email, String firstName, String lastName, Date hireDate, String phoneNumber, String position, int salaryId) {
        this.id = id;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.departmentId = departmentId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.salaryId = salaryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }
}

