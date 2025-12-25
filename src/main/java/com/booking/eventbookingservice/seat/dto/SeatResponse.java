package com.booking.eventbookingservice.seat.dto;

import com.booking.eventbookingservice.seat.entity.SeatCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatResponse {
    private Long id;
    private String rowLabel;
    private Integer seatNumber;
    private SeatCategory category;
    private Long auditoriumId;
}