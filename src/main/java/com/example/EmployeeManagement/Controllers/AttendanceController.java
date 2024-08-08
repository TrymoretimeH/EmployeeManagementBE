package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Between.UserInfoDetails;
import com.example.EmployeeManagement.Entities.Attendance;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Entities.UserInfo;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.AttendanceService;
import com.example.EmployeeManagement.Services.EmployeeService;
import com.example.EmployeeManagement.Utils.DateTimeUtil;
import com.example.EmployeeManagement.Utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/attendance")
//@CrossOrigin(origins = "*")

public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<Attendance> getAllAttendance() {
        UserInfoDetails userInfoDetails = (UserInfoDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = userInfoDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        if (isAdmin) {
            List<Attendance> list = attendanceService.getAllAttendances();
            for (Attendance attendance : list) {
                attendance.setImage(ImageUtil.decompressImage(attendance.getImage()));
            }
            return list;
        }

        boolean isUser = userInfoDetails.getAuthorities().contains(new SimpleGrantedAuthority("USER"));
        if (isUser) {
            int id = userInfoDetails.getEmployee().getId();

            List<Attendance> list = attendanceService.getAttendanceByEmployeeId(id);
            for (Attendance attendance : list) {
                attendance.setImage(ImageUtil.decompressImage(attendance.getImage()));
            }
            return list;
        }
        return new ArrayList<>();
    }



    @PostMapping("/add")
    public ResponseEntity<?> addAttendance(@RequestParam("image") MultipartFile file, int employeeId, Date checkInTime, Date checkOutTime, Date attendanceDate) throws IOException {
        Optional<Employee> existE = employeeService.getEmployeeById(employeeId);
        if (!existE.isPresent()) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Could not find employee", null);
        } else {
            Attendance attendance = new Attendance(existE.get(), checkInTime, checkOutTime, attendanceDate, ImageUtil.compressImage(file.getBytes()));
            Optional<Attendance> existD = attendanceService.getAttendanceById(attendance.getAttendanceId());
            if (existD.isPresent()) {
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Attendance already exists", attendance);
            } else {
                attendanceService.add(attendance);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Create a new attendance successfully", attendance);
            }
        }

//        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Attendance already exists", attendance);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<?> updateAttendance(MultipartFile image, int employeeId, int attendanceId, Date checkInTime, Date checkOutTime, Date attendanceDate) throws IOException {
        Optional<Employee> existE = employeeService.getEmployeeById(employeeId);
        if (existE.isEmpty()) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Could not find employee", null);
        } else {
            Optional<Attendance> existD = attendanceService.getAttendanceById(attendanceId);
            if (existD.isPresent()) {
                Attendance attendance = existD.get();
                if (image != null) {
                    attendance.setImage(ImageUtil.compressImage(image.getBytes()));
                }
                if (checkInTime != null) {
                    attendance.setCheckInTime(checkInTime);
                }
                if (checkOutTime != null) {
                    attendance.setCheckOutTime(checkOutTime);
                }
                if (attendanceDate != null) {
                    attendance.setAttendanceDate(attendanceDate);
                }
                attendance.setEmployee(existE.get());
                //            Attendance attendance = new Attendance(attendanceId, employeeId, checkInTime, checkOutTime, attendanceDate, ImageUtil.compressImage(file.getBytes()));

                attendanceService.update(attendance);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update a attendance successfully", attendance);
            } else {
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found attendance", null);
            }
        }



    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable int id) {
        Optional<Attendance> existD = attendanceService.getAttendanceById(id);
        if (existD.isPresent()) {
            attendanceService.delete(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Attendance deleted successfully", null);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found attendance", null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchAttendance(@RequestBody String name) {
//        return ResponseEntity.ok().body(attendanceService.findByName(name));
        return ResponseEntity.ok().body("CHECK SEARCH");
    }

}
