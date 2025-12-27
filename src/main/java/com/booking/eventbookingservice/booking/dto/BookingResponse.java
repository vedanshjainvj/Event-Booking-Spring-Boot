package com.booking.eventbookingservice.booking.dto;

import com.booking.eventbookingservice.booking.entity.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class BookingResponse {
    private Long bookingId;
    private Long showId;
    private List<Long> seats;
    private BookingStatus status;
    private Instant createdAt;
}