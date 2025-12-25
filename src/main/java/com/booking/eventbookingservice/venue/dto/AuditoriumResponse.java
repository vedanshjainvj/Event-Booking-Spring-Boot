package com.booking.eventbookingservice.venue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditoriumResponse {
    private Long id;
    private String name;
    private Long venueId;
    private String venueName;
}