package com.booking.eventbookingservice.booking.repository;

import com.booking.eventbookingservice.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}