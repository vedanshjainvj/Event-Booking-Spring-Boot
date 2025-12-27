package com.booking.eventbookingservice.booking.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishBookingConfirmed(BookingConfirmedEvent event) {
        kafkaTemplate.send(BookingTopics.BOOKING_CONFIRMED, event);
    }
}