package com.booking.eventbookingservice.event.service;

import com.booking.eventbookingservice.event.dto.UpdateEventRequest;
import com.booking.eventbookingservice.event.entity.Event;
import com.booking.eventbookingservice.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event create(Event event){
        return eventRepository.save(event);
    }

    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    public Event updateEvent(Long eventId, UpdateEventRequest request) {

        var event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLanguage(request.getLanguage());
        event.setGenre(request.getGenre());
        event.setDurationMinutes(request.getDurationMinutes());
        event.setRating(request.getRating());

        return eventRepository.save(event);
    }

}