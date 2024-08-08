package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Entities.Attendance;
import com.example.EmployeeManagement.Repositories.AttendanceRepository;
import com.example.EmployeeManagement.Repositories.AttendanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(int id) {
        return attendanceRepository.findById(id);
    }

    public void add(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    public void update(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    public void delete(int id) {
        attendanceRepository.deleteById(id);
    }

    public List<Attendance> getAttendanceByEmployeeId(int id) {
        return attendanceRepository.findByEmployeeId(id);
    }

    @Transactional
    public void deleteAttendanceByEmployeeId(int id) {
        List<Attendance> attendances = getAttendanceByEmployeeId(id);
        attendanceRepository.deleteAll(attendances);
    }
}
