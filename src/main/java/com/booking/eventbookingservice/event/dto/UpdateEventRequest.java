package com.booking.eventbookingservice.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEventRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String language;

    @NotBlank
    private String genre;

    @NotNull
    private Integer durationMinutes;

    private Double rating;
}