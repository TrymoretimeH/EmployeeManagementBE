package com.example.EmployeeManagement.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SALARY")
public class Salary {
    @Id
    @Column(name = "SALARY_ID")
    private int salaryId;

    @Column(name = "BASE_SALARY")
    private int baseSalary;

    @Column(name = "ALLOWANCE")
    private int allowance;

    @Column(name = "DEDUCTIONS")
    private int deductions;

    public Salary() {
    }

    public Salary(int baseSalary, int allowance, int deductions) {
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.deductions = deductions;
    }

    public Salary(int salaryId, int baseSalary, int allowance, int deductions) {
        this.salaryId = salaryId;
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.deductions = deductions;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getDeductions() {
        return deductions;
    }

    public void setDeductions(int deductions) {
        this.deductions = deductions;
    }
}
