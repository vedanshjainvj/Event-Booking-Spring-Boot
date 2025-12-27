package com.booking.eventbookingservice.event.controller;

import com.booking.eventbookingservice.common.ApiResponse;
import com.booking.eventbookingservice.event.service.EventBrowseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/browse/events")
@RequiredArgsConstructor
public class EventBrowseController {

    private final EventBrowseService browseService;

    @GetMapping("/language/{language}")
    public ResponseEntity<ApiResponse<?>> getByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Events fetched by language")
                        .data(browseService.getByLanguage(language))
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<ApiResponse<?>> getByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Events fetched by genre")
                        .data(browseService.getByGenre(genre))
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<ApiResponse<?>> getByCity(@PathVariable String city) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Events fetched by city")
                        .data(browseService.getByCity(city))
                        .timestamp(Instant.now())
                        .build()
        );
    }

    @GetMapping("/date")
    public ResponseEntity<ApiResponse<?>> getByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Events fetched by date")
                        .data(browseService.getByDate(date))
                        .timestamp(Instant.now())
                        .build()
        );
    }
}