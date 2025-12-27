package com.booking.eventbookingservice.booking.dto;

import com.booking.eventbookingservice.booking.entity.SeatState;
import com.booking.eventbookingservice.seat.entity.SeatCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatAvailabilityResponse {

    private Long seatId;
    private String rowLabel;
    private Integer seatNumber;
    private SeatCategory category;
    private SeatState state;
}