package com.booking.eventbookingservice.event.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventSummaryResponse {
    private Long id;
    private String title;
    private String language;
    private String genre;
    private double rating;
}