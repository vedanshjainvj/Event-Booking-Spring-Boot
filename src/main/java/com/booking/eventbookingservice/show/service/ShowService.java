package com.booking.eventbookingservice.show.service;

import com.booking.eventbookingservice.event.repository.EventRepository;
import com.booking.eventbookingservice.show.dto.CreateShowRequest;
import com.booking.eventbookingservice.show.dto.ShowResponse;
import com.booking.eventbookingservice.show.entity.Show;
import com.booking.eventbookingservice.show.repository.ShowRepository;
import com.booking.eventbookingservice.venue.repository.AuditoriumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final EventRepository eventRepository;
    private final AuditoriumRepository auditoriumRepository;

    public ShowResponse create(CreateShowRequest request){

        var event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        var auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        var show = Show.builder()
                .event(event)
                .auditorium(auditorium)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .basePrice(request.getBasePrice())
                .build();

        var saved = showRepository.save(show);

        return ShowResponse.builder()
                .id(saved.getId())
                .eventId(event.getId())
                .eventTitle(event.getTitle())
                .auditoriumId(auditorium.getId())
                .auditoriumName(auditorium.getName())
                .startTime(saved.getStartTime())
                .endTime(saved.getEndTime())
                .basePrice(saved.getBasePrice())
                .build();
    }

    public List<ShowResponse> getByEvent(Long eventId){
        return showRepository.findByEventId(eventId)
                .stream()
                .map(s -> ShowResponse.builder()
                        .id(s.getId())
                        .eventId(s.getEvent().getId())
                        .eventTitle(s.getEvent().getTitle())
                        .auditoriumId(s.getAuditorium().getId())
                        .auditoriumName(s.getAuditorium().getName())
                        .startTime(s.getStartTime())
                        .endTime(s.getEndTime())
                        .basePrice(s.getBasePrice())
                        .build())
                .toList();
    }
}