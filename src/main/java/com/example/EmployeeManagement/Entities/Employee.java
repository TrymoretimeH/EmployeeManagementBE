package com.example.EmployeeManagement.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.parameters.P;

import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

//    @Column(name = "ADDRESS")
//    private String address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PROVINCE_CODE", referencedColumnName = "CODE")
    private Province province;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "DISTRICT_CODE", referencedColumnName = "CODE")
    private District district;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "WARD_CODE", referencedColumnName = "CODE")
    private Ward ward;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "HIRE_DATE")
    private Date hireDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "DEPARTMENT_ID")
    @JsonIgnoreProperties({"employeeList", "manager"})
//    @JsonBackReference
    private Department department;

    @Column(name = "SALARY_ID")
    private int salaryId;


    public Employee() {}

    public Employee(int id, String firstName, String lastName, Date dateOfBirth, Province provinceCode, District districtCode, Ward wardCode, String phoneNumber, String email, String position, Date hireDate, Department department, int salaryId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.province = provinceCode;
        this.district = districtCode;
        this.ward = wardCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.hireDate = hireDate;
        this.department = department;
        this.salaryId = salaryId;
    }

    public Employee(String firstName, String lastName, Date dateOfBirth, Province provinceCode, District districtCode, Ward wardCode, String phoneNumber, String email, String position, Date hireDate, Department department, int salaryId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.province = provinceCode;
        this.district = districtCode;
        this.ward = wardCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.hireDate = hireDate;
        this.department = department;
        this.salaryId = salaryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }


    public Province getProvince() {
//        if (province == null) {
//            province = new Province();
//        }
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
//        if (district == null) {
//            district = new District();
//        }
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward getWard() {
//        if (ward == null) {
//            ward = new Ward();
//        }
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }
}
