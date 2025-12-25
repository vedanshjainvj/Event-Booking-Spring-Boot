package com.booking.eventbookingservice.seat.controller;

import com.booking.eventbookingservice.common.ApiResponse;
import com.booking.eventbookingservice.seat.dto.CreateSeatRequest;
import com.booking.eventbookingservice.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody CreateSeatRequest request){

        var saved = seatService.create(request);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Seat created successfully")
                        .data(saved)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/{auditoriumId}")
    public ResponseEntity<ApiResponse<?>> getSeats(@PathVariable Long auditoriumId){

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Seats fetched")
                        .data(seatService.getSeats(auditoriumId))
                        .timestamp(Instant.now())
                        .build()
        );
    }
}