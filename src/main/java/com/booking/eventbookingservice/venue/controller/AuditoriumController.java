package com.booking.eventbookingservice.venue.controller;

import com.booking.eventbookingservice.common.ApiResponse;
import com.booking.eventbookingservice.venue.dto.CreateAuditoriumRequest;
import com.booking.eventbookingservice.venue.service.AuditoriumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/auditoriums")
@RequiredArgsConstructor
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody CreateAuditoriumRequest request){

        var saved = auditoriumService.create(request);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Auditorium created successfully")
                        .data(saved)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Auditoriums fetched")
                        .data(auditoriumService.getAll())
                        .timestamp(Instant.now())
                        .build()
        );
    }
}