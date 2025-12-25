package com.booking.eventbookingservice.venue.repository;

import com.booking.eventbookingservice.venue.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
