package com.example.EmployeeManagement.Services;

import com.example.EmployeeManagement.Between.UserInfoDetails;
import com.example.EmployeeManagement.Entities.UserInfo;
import com.example.EmployeeManagement.Repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserInfoDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = userInfoRepository.findByEmail(email);
        Optional<UserInfoDetails> userInfoDetails = userDetail.map(UserInfoDetails::new);
        if (userInfoDetails.isPresent()) {
            System.out.println("CHECK ROLES: " + userInfoDetails.get().getAuthorities());
            return userInfoDetails.get();
        } else {
            throw new UsernameNotFoundException("Email not found" + email);
        }
//        return userDetail.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }

}
