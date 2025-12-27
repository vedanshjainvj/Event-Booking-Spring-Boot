package com.booking.eventbookingservice.booking.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookingEventConsumer {

    @KafkaListener(topics = BookingTopics.BOOKING_CONFIRMED, groupId = "booking-service-group")
    public void handleBookingConfirmed(BookingConfirmedEvent event) {

        log.info("📩 Booking Confirmed Event Received");
        log.info("Booking ID: {}", event.getBookingId());
        log.info("User ID: {}", event.getUserId());
        log.info("Show ID: {}", event.getShowId());
        log.info("Seats: {}", event.getSeatIds());

        // Simulate Email + SMS
        log.info("📧 Sending Email confirmation…");
        log.info("📱 Sending SMS notification…");
    }
}