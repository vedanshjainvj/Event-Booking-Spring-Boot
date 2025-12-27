package com.booking.eventbookingservice.booking.dto;

import com.booking.eventbookingservice.booking.entity.SeatState;
import com.booking.eventbookingservice.seat.entity.SeatCategory;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SeatAvailabilityResponse  implements Serializable {

    private Long seatId;
    private String rowLabel;
    private Integer seatNumber;
    private SeatCategory category;
    private SeatState state;
}