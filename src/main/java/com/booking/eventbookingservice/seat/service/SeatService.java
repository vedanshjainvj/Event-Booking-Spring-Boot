package com.booking.eventbookingservice.seat.service;

import com.booking.eventbookingservice.seat.dto.CreateSeatRequest;
import com.booking.eventbookingservice.seat.dto.SeatResponse;
import com.booking.eventbookingservice.seat.entity.Seat;
import com.booking.eventbookingservice.seat.repository.SeatRepository;
import com.booking.eventbookingservice.venue.repository.AuditoriumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final AuditoriumRepository auditoriumRepository;

    public SeatResponse create(CreateSeatRequest request){

        var auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        var seat = Seat.builder()
                .rowLabel(request.getRowLabel())
                .seatNumber(request.getSeatNumber())
                .category(request.getCategory())
                .auditorium(auditorium)
                .build();

        var saved = seatRepository.save(seat);

        return SeatResponse.builder()
                .id(saved.getId())
                .rowLabel(saved.getRowLabel())
                .seatNumber(saved.getSeatNumber())
                .category(saved.getCategory())
                .auditoriumId(saved.getAuditorium().getId())
                .build();
    }

    public List<SeatResponse> getSeats(Long auditoriumId){
        return seatRepository.findByAuditoriumId(auditoriumId)
                .stream()
                .map(s -> SeatResponse.builder()
                        .id(s.getId())
                        .rowLabel(s.getRowLabel())
                        .seatNumber(s.getSeatNumber())
                        .category(s.getCategory())
                        .auditoriumId(s.getAuditorium().getId())
                        .build()
                )
                .toList();
    }
}