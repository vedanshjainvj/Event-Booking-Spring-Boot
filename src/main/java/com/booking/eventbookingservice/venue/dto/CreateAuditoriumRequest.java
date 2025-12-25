package com.booking.eventbookingservice.venue.dto;

import lombok.Data;

@Data
public class CreateAuditoriumRequest {
    private String name;
    private Long venueId;
}