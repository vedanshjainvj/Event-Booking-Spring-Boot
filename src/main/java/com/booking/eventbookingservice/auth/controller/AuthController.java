package com.booking.eventbookingservice.auth.controller;

import com.booking.eventbookingservice.auth.dto.RegisterRequest;
import com.booking.eventbookingservice.auth.service.AuthService;
import com.booking.eventbookingservice.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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
}