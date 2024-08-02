package com.example.EmployeeManagement.Services;


import com.example.EmployeeManagement.Entities.RefreshToken;
import com.example.EmployeeManagement.Entities.UserInfo;
import com.example.EmployeeManagement.Exceptions.TokenRefreshException;
import com.example.EmployeeManagement.Repositories.RefreshTokenRepository;
import com.example.EmployeeManagement.Repositories.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Integer userId) {
        RefreshToken refreshToken = new RefreshToken();

        Optional<RefreshToken> eRT = refreshTokenRepository.findByUserId(userId);
        if (eRT.isPresent()) {
            refreshToken = eRT.get();
        } else {
            Optional<UserInfo> eU = userInfoRepository.findById(userId);
            if (eU.isPresent()) {
                refreshToken.setUser(eU.get());
                refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
                refreshToken.setToken(UUID.randomUUID().toString());

                refreshToken = refreshTokenRepository.save(refreshToken);
            } else {
                System.out.println("USER NOT FOUND");
            }
        }

        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Integer userId) {
        Optional<UserInfo> eU = userInfoRepository.findById(userId);
        return eU.map(userInfo -> refreshTokenRepository.deleteByUser(userInfo)).orElse(0);
    }

}
