package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Between.AuthRequest;
import com.example.EmployeeManagement.Between.UserInfoDetails;
import com.example.EmployeeManagement.Entities.UserInfo;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Services.JwtService;
import com.example.EmployeeManagement.Services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/welcome")
    public String welcome() {
        return"Welcome to EmployeeManagement";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo user) {
        return service.addUser(user);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('USER')")
    public String userProfile() {
        return "User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminProfile() {
        return "Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(), authRequest.getPassword()
        ));
        if (authentication.isAuthenticated()) {
            Map<String, String> tokenMap = new HashMap<>();
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
            System.out.println("CHECK AFTER AUTHENTICATION");
            System.out.println(userInfoDetails.getEmpId());
            tokenMap.put("token", jwtService.generateToken(authRequest.getEmail(), userInfoDetails.getEmpId(), authentication.getAuthorities()));
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Generate token successfully", tokenMap);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Generate token failure", null);
        }

    }

}
