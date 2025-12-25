package com.booking.eventbookingservice.venue.service;

import com.booking.eventbookingservice.venue.entity.Venue;
import com.booking.eventbookingservice.venue.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public Venue create(Venue venue){
        return venueRepository.save(venue);
    }

    public List<Venue> getAll(){
        return venueRepository.findAll();
    }
}