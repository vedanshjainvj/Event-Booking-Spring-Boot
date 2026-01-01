package com.booking.eventbookingservice.auth.service;

import com.booking.eventbookingservice.auth.entity.RefreshToken;
import com.booking.eventbookingservice.auth.repository.RefreshTokenRepository;
import com.booking.eventbookingservice.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    private final long REFRESH_TOKEN_VALIDITY_MS = 1000L * 60 * 60 * 24 * 7;  // 7 days

    public RefreshToken createRefreshToken(Long userId) {

        refreshTokenRepository.deleteByUser_Id(userId);

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RefreshToken token = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(REFRESH_TOKEN_VALIDITY_MS))
                .user(user)
                .build();

        return refreshTokenRepository.save(token);
    }

    public RefreshToken validateRefreshToken(String token) {

        var refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired, please login again");
        }

        return refreshToken;
    }
}