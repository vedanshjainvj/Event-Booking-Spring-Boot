package com.booking.eventbookingservice.event.service;

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
}