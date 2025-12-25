package com.booking.eventbookingservice.venue.controller;

import com.booking.eventbookingservice.common.ApiResponse;
import com.booking.eventbookingservice.venue.entity.Venue;
import com.booking.eventbookingservice.venue.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody Venue venue){

        var saved = venueService.create(venue);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Venue created successfully")
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
                        .message("Venues fetched")
                        .data(venueService.getAll())
                        .timestamp(Instant.now())
                        .build()
        );
    }
}