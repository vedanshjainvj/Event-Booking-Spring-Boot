package com.booking.eventbookingservice.venue.repository;

import com.booking.eventbookingservice.venue.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}