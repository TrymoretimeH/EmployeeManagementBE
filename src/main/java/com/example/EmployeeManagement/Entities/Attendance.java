package com.example.EmployeeManagement.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "ATTENDANCE")
public class Attendance {
    @Id
    @Column(name = "ATTENDANCE_ID")
    private int attendanceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHECK_IN_TIME")
    private Date checkInTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHECK_OUT_TIME")
    private Date checkOutTime;

    @Column(name = "ATTENDANCE_DATE")
    private Date attendanceDate;

    @Lob
    @Column(name = "IMAGE", length = 4000)
    private byte[] image;

    public Attendance() {
    }

    public Attendance(Date checkInTime, Date checkOutTime, Date attendanceDate, byte[] image) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.attendanceDate = attendanceDate;
        this.image = image;
    }

    public Attendance(Employee employee, Date checkInTime, Date checkOutTime, Date attendanceDate, byte[] image) {
        this.employee = employee;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.attendanceDate = attendanceDate;
        this.image = image;
    }

    public Attendance(int attendanceId, Employee employee, Date checkInTime, Date checkOutTime, Date attendanceDate, byte[] image) {
        this.attendanceId = attendanceId;
        this.employee = employee;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.attendanceDate = attendanceDate;
        this.image = image;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
