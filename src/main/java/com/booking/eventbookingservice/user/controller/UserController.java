package com.booking.eventbookingservice.user.controller;

import com.booking.eventbookingservice.auth.entity.User;
import com.booking.eventbookingservice.auth.repository.UserRepository;
import com.booking.eventbookingservice.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> me(Authentication authentication) {

        System.out.println("========================================");
        System.out.println("🎯 /me ENDPOINT HIT!");
        System.out.println("📍 Authentication object: " + authentication);

        // Check if authentication exists
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("❌ Authentication is null or not authenticated");
            System.out.println("========================================");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ApiResponse.builder()
                            .success(false)
                            .message("Not authenticated")
                            .timestamp(Instant.now())
                            .build()
            );
        }

        String email = authentication.getName();
        System.out.println("📧 Authenticated user email: " + email);
        System.out.println("🔑 Authorities: " + authentication.getAuthorities());

        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("❌ User not found with email: " + email);
                    System.out.println("========================================");
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "User not found"
                    );
                });

        System.out.println("✅ User found: " + user.getEmail());
        System.out.println("========================================");

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("User profile fetched successfully")
                        .data(Map.of(
                                "id", user.getId(),
                                "name", user.getName(),
                                "email", user.getEmail(),
                                "phone", user.getPhone(),
                                "role", user.getRole()
                        ))
                        .timestamp(Instant.now())
                        .build()
        );
    }
}