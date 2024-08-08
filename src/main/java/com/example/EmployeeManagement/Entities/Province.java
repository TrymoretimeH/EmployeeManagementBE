package com.example.EmployeeManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PROVINCES")
public class Province {
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

    @Column(name = "ADMINISTRATIVE_UNIT_ID")
    private int administrativeUnitId;

    @Column(name = "ADMINISTRATIVE_REGION_ID")
    private int administrativeRegionId;

    public Province(String code, String name, String nameEn, String fullName, String fullNameEn, String codeName, int administrativeUnitId, int administrativeRegionId) {
        this.code = code;
        this.name = name;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.fullNameEn = fullNameEn;
        this.codeName = codeName;
        this.administrativeUnitId = administrativeUnitId;
        this.administrativeRegionId = administrativeRegionId;
    }

    public Province() {
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

    public int getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(int administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
    }

    public int getAdministrativeRegionId() {
        return administrativeRegionId;
    }

    public void setAdministrativeRegionId(int administrativeRegionId) {
        this.administrativeRegionId = administrativeRegionId;
    }
}
