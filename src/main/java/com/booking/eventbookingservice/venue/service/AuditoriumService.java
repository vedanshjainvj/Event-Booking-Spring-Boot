package com.booking.eventbookingservice.venue.service;

import com.booking.eventbookingservice.venue.dto.AuditoriumResponse;
import com.booking.eventbookingservice.venue.dto.CreateAuditoriumRequest;
import com.booking.eventbookingservice.venue.entity.Auditorium;
import com.booking.eventbookingservice.venue.repository.AuditoriumRepository;
import com.booking.eventbookingservice.venue.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final VenueRepository venueRepository;

    public Auditorium create(CreateAuditoriumRequest request){

        var venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        var auditorium = Auditorium.builder()
                .name(request.getName())
                .venue(venue)
                .build();

        return auditoriumRepository.save(auditorium);
    }

    public List<AuditoriumResponse> getAll() {
        return auditoriumRepository.findAll()
                .stream()
                .map(a -> AuditoriumResponse.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .venueId(a.getVenue().getId())
                        .venueName(a.getVenue().getName())
                        .build()
                )
                .toList();
    }

}