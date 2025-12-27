package com.booking.eventbookingservice.show.controller;

import com.booking.eventbookingservice.common.ApiResponse;
import com.booking.eventbookingservice.show.dto.CreateShowRequest;
import com.booking.eventbookingservice.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody CreateShowRequest request){

        var saved = showService.create(request);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Show created successfully")
                        .data(saved)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<ApiResponse<?>> getByEvent(@PathVariable Long eventId){

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Shows fetched")
                        .data(showService.getByEvent(eventId))
                        .timestamp(Instant.now())
                        .build()
        );
    }
}