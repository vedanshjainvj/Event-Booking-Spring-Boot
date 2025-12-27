package com.booking.eventbookingservice.show.dto;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class ShowResponse implements Serializable {

    private Long id;
    private Long eventId;
    private String eventTitle;
    private Long auditoriumId;
    private String auditoriumName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double basePrice;
}