package com.example.EmployeeManagement.Controllers;

import com.example.EmployeeManagement.Between.AuthRequest;
import com.example.EmployeeManagement.Between.UserInfoDetails;
import com.example.EmployeeManagement.Entities.Employee;
import com.example.EmployeeManagement.Entities.RefreshToken;
import com.example.EmployeeManagement.Entities.UserInfo;
import com.example.EmployeeManagement.Exceptions.TokenRefreshException;
import com.example.EmployeeManagement.Handlers.ResponseHandler;
import com.example.EmployeeManagement.Payload.Request.SignupRequest;
import com.example.EmployeeManagement.Payload.Response.UserInfoResponse;
import com.example.EmployeeManagement.Repositories.UserInfoRepository;
import com.example.EmployeeManagement.Services.JwtService;
import com.example.EmployeeManagement.Services.RefreshTokenService;
import com.example.EmployeeManagement.Services.UserInfoService;
import com.example.EmployeeManagement.Utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoService userInfoService;

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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(), authRequest.getPassword()
        ));
        if (authentication.isAuthenticated()) {
//            Map<String, String> tokenMap = new HashMap<>();
            UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();


//            dataMap.put("id", String.valueOf(userInfoDetails.getId()));
//            dataMap.put("name", userInfoDetails.getUsername());
//            dataMap.put("roles", userInfoDetails.getAuthorities().toString());
//            empMap.put("employee", userInfoDetails.getEmployee());
            UserInfoResponse userInfoResponse = new UserInfoResponse();
            userInfoResponse.setEmployee(userInfoDetails.getEmployee());
            userInfoResponse.setId(userInfoDetails.getId());
            userInfoResponse.setName(userInfoDetails.getUsername());
            userInfoResponse.setRoles(userInfoDetails.getAuthorities());


            ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userInfoDetails);

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDetails.getId());

            ResponseCookie jwtRefreshCookie = jwtUtil.generateJwtRefreshCookie(refreshToken.getToken());

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString() + "; Partitioned")
                            .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString() + "; Partitioned")
                                    .body(userInfoResponse);

//            tokenMap.put("token", jwtService.generateToken(authRequest.getEmail(), userInfoDetails.getEmployee(), authentication.getAuthorities()));
//            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Generate token successfully", tokenMap);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Generate token failure", null);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        Optional<UserInfo> eU = userInfoRepository.findByEmail(signupRequest.getEmail());
        if (eU.isPresent()) {
            return ResponseHandler.generateResponse(HttpStatus.CONFLICT, false, "Email already in use", null);
        } else {
            UserInfo userInfo = new UserInfo(signupRequest.getName(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()), signupRequest.getRoles(), null);
            userInfoService.addUser(userInfo);
            return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "User created", null);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(principle.toString(), "anonymousUser")) {
            int userId = ((UserInfoDetails) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }

        ResponseCookie jwtCookie = jwtUtil.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtil.getCleanJwtRefreshCookie();

        Map<String, Object> mesMap = new HashMap<>();

        mesMap.put("message", "You've been signed out!");

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(mesMap);

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtil.getJwtRefreshFromCookies(request);

        Map<String, String> mesMap = new HashMap<>();
        mesMap.put("refreshToken", "TOKEN is refreshed successfully!");

        if ((refreshToken != null) && (!refreshToken.isEmpty())) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString() + "; Partitioned")
                                .body(mesMap);
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh Token is not in database!"));
        }

        return ResponseEntity.badRequest().body("Refresh Token is empty!");
    }

}
