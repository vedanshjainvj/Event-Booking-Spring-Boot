package com.booking.eventbookingservice.auth.controller;

import com.booking.eventbookingservice.auth.dto.LoginRequest;
import com.booking.eventbookingservice.auth.dto.RefreshTokenRequest;
import com.booking.eventbookingservice.auth.dto.RegisterRequest;
import com.booking.eventbookingservice.auth.service.AuthService;
import com.booking.eventbookingservice.auth.service.JwtService;
import com.booking.eventbookingservice.auth.service.RefreshTokenService;
import com.booking.eventbookingservice.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("User registered successfully")
                        .timestamp(Instant.now())
                        .build()
        );

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequest request) {

        var tokens = authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Login successful")
                        .data(tokens)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh(@RequestBody RefreshTokenRequest request) {

        var tokenEntity = refreshTokenService.validateRefreshToken(request.getRefreshToken());

        String newAccessToken =
                jwtService.generateToken(
                        tokenEntity.getUser().getEmail(),
                        Map.of("role", tokenEntity.getUser().getRole().name())
                );

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Token refreshed successfully")
                        .data(Map.of("accessToken", newAccessToken))
                        .timestamp(Instant.now())
                        .build()
        );
    }


}