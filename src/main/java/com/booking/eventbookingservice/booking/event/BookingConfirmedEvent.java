package com.booking.eventbookingservice.booking.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingConfirmedEvent implements Serializable {

    private Long bookingId;
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
    private Instant timestamp;
}