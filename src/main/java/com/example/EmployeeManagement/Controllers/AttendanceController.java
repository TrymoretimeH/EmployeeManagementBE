package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Entities.Attendance;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.AttendanceService;
import com.example.EmployeeManagement.Utils.DateTimeUtil;
import com.example.EmployeeManagement.Utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")

public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        List<Attendance> list = attendanceService.getAllAttendances();
        for (Attendance attendance : list) {
            attendance.setImage(ImageUtil.decompressImage(attendance.getImage()));
        }
        return list;
    }



    @PostMapping("/add")
    public ResponseEntity<?> addAttendance(@RequestParam("image") MultipartFile file, int employeeId, Date checkInTime, Date checkOutTime, Date attendanceDate) throws IOException {
        Attendance attendance = new Attendance(employeeId, checkInTime, checkOutTime, attendanceDate, ImageUtil.compressImage(file.getBytes()));
        Optional<Attendance> existD = attendanceService.getAttendanceById(attendance.getAttendanceId());
        if (existD.isPresent()) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Attendance already exists", attendance);
        } else {
            attendanceService.add(attendance);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Create a new attendance successfully", attendance);
        }
//        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Attendance already exists", attendance);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<?> updateAttendance(MultipartFile image, int employeeId, int attendanceId, Date checkInTime, Date checkOutTime, Date attendanceDate) throws IOException {
        Optional<Attendance> existD = attendanceService.getAttendanceById(attendanceId);
        if (existD.isPresent()) {
            Attendance attendance = existD.get();
            if (image != null) {
                System.out.println("UPDATE IMAGE!");
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
            attendance.setEmployeeId(employeeId);
            //            Attendance attendance = new Attendance(attendanceId, employeeId, checkInTime, checkOutTime, attendanceDate, ImageUtil.compressImage(file.getBytes()));

            attendanceService.update(attendance);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update a attendance successfully", attendance);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "Can not found attendance", null);
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
