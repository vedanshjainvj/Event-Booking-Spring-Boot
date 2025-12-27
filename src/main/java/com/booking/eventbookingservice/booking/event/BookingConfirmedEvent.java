package com.booking.eventbookingservice.booking.event;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class BookingConfirmedEvent implements Serializable {

    private Long bookingId;
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
    private Instant timestamp;
}