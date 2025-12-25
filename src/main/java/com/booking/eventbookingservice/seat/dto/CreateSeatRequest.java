package com.booking.eventbookingservice.seat.dto;

import com.booking.eventbookingservice.seat.entity.SeatCategory;
import lombok.Data;

@Data
public class CreateSeatRequest {

    private String rowLabel;
    private Integer seatNumber;
    private SeatCategory category;
    private Long auditoriumId;
}