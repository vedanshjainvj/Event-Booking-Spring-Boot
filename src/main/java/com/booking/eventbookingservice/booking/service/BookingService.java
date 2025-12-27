package com.booking.eventbookingservice.booking.service;

import com.booking.eventbookingservice.auth.repository.UserRepository;
import com.booking.eventbookingservice.booking.dto.CreateBookingRequest;
import com.booking.eventbookingservice.booking.dto.BookingResponse;
import com.booking.eventbookingservice.booking.entity.*;
import com.booking.eventbookingservice.booking.repository.BookingRepository;
import com.booking.eventbookingservice.booking.repository.ShowSeatStatusRepository;
import com.booking.eventbookingservice.show.repository.ShowRepository;
import com.booking.eventbookingservice.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.booking.eventbookingservice.booking.dto.SeatAvailabilityResponse;
import com.booking.eventbookingservice.booking.entity.SeatState;
import org.springframework.cache.annotation.Cacheable;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatStatusRepository statusRepository;
    private final UserRepository userRepository;

    @Transactional
    @CacheEvict(value = "showAvailability", key = "#request.showId")
    public BookingResponse book(CreateBookingRequest request){


        var authUser = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(authUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        var seats = seatRepository.findAllById(request.getSeatIds());

        if(seats.size() != request.getSeatIds().size())
            throw new RuntimeException("One or more seats not found");

        seats.forEach(seat -> {
            if(!seat.getAuditorium().getId().equals(show.getAuditorium().getId())) {
                throw new RuntimeException("Seat does not belong to this show's auditorium");
            }
        });

        var existing = statusRepository
                .findByShowIdAndSeatIdIn(request.getShowId(), request.getSeatIds());

        if(!existing.isEmpty())
            throw new RuntimeException("One or more seats already booked");

        for(var seat : seats){
            var status = ShowSeatStatus.builder()
                    .show(show)
                    .seat(seat)
                    .state(SeatState.BOOKED)
                    .build();

            statusRepository.save(status);
        }

        var booking = Booking.builder()
                .user(user)
                .show(show)
                .seatIds(request.getSeatIds())
                .createdAt(Instant.now())
                .status(BookingStatus.CONFIRMED)
                .build();

        var saved = bookingRepository.save(booking);

        return BookingResponse.builder()
                .bookingId(saved.getId())
                .showId(show.getId())
                .seats(saved.getSeatIds())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Cacheable(
            value = "showAvailability",
            key = "#showId"
    )
    public List<SeatAvailabilityResponse> getAvailability(Long showId){

        var show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        var auditoriumId = show.getAuditorium().getId();

        var seats = seatRepository.findByAuditoriumId(auditoriumId);

        var booked = statusRepository
                .findByShowIdAndSeatIdIn(
                        showId,
                        seats.stream().map(s -> s.getId()).toList()
                )
                .stream()
                .map(s -> s.getSeat().getId())
                .toList();

        return seats.stream().map(seat ->
                SeatAvailabilityResponse.builder()
                        .seatId(seat.getId())
                        .rowLabel(seat.getRowLabel())
                        .seatNumber(seat.getSeatNumber())
                        .category(seat.getCategory())
                        .state(
                                booked.contains(seat.getId())
                                        ? SeatState.BOOKED
                                        : SeatState.AVAILABLE
                        )
                        .build()
        ).toList();
    }

}