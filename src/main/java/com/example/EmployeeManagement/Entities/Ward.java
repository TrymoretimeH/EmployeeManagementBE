package com.example.EmployeeManagement.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WARDS")
public class Ward {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "FULL_NAME_EN")
    private String fullNameEn;

    @Column(name = "CODE_NAME")
    private String codeName;

    @Column(name = "DISTRICT_CODE")
    private String districtCode;

    @Column(name = "ADMINISTRATIVE_UNIT_ID")
    private int administrativeRegionId;

    public Ward() {
    }

    public Ward(String code, String name, String nameEn, String fullName, String fullNameEn, String codeName, String districtCode, int administrativeRegionId) {
        this.code = code;
        this.name = name;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.fullNameEn = fullNameEn;
        this.codeName = codeName;
        this.districtCode = districtCode;
        this.administrativeRegionId = administrativeRegionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public int getAdministrativeRegionId() {
        return administrativeRegionId;
    }

    public void setAdministrativeRegionId(int administrativeRegionId) {
        this.administrativeRegionId = administrativeRegionId;
    }
}
