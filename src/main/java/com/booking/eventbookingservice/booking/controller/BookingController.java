package com.booking.eventbookingservice.booking.controller;

import com.booking.eventbookingservice.booking.dto.CreateBookingRequest;
import com.booking.eventbookingservice.booking.service.BookingService;
import com.booking.eventbookingservice.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> book(@RequestBody CreateBookingRequest request){

        var res = bookingService.book(request);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Booking successful")
                        .data(res)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/availability/{showId}")
    public ResponseEntity<ApiResponse<?>> availability(@PathVariable Long showId){

        var res = bookingService.getAvailability(showId);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Seat availability fetched")
                        .data(res)
                        .timestamp(Instant.now())
                        .build()
        );
    }

}