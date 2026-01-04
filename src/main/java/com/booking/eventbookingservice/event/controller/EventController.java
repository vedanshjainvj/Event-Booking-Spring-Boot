package com.booking.eventbookingservice.event.controller;

import com.booking.eventbookingservice.common.ApiResponse;
import com.booking.eventbookingservice.event.dto.UpdateEventRequest;
import com.booking.eventbookingservice.event.entity.Event;
import com.booking.eventbookingservice.event.repository.EventRepository;
import com.booking.eventbookingservice.event.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody Event event){
        var saved = eventService.create(event);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Event created successfully")
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
                        .message("Events fetched")
                        .data(eventService.getAll())
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<?>> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody UpdateEventRequest request
    ) {
        var updated = eventService.updateEvent(eventId, request);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Event updated successfully")
                        .data(updated)
                        .timestamp(Instant.now())
                        .build()
        );
    }
}