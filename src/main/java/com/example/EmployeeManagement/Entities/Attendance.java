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

    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

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

    public Attendance(int employeeId, Date checkInTime, Date checkOutTime, Date attendanceDate, byte[] image) {
        this.employeeId = employeeId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.attendanceDate = attendanceDate;
        this.image = image;
    }

    public Attendance(int attendanceId, int employeeId, Date checkInTime, Date checkOutTime, Date attendanceDate, byte[] image) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
