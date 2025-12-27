package com.booking.eventbookingservice.show.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateShowRequest {
    private Long eventId;
    private Long auditoriumId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double basePrice;
}