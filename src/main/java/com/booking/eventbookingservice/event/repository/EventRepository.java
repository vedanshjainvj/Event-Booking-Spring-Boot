package com.booking.eventbookingservice.event.repository;

import com.booking.eventbookingservice.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}