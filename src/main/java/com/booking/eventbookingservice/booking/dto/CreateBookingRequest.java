package com.booking.eventbookingservice.booking.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateBookingRequest {
    private Long showId;
    private List<Long> seatIds;
}