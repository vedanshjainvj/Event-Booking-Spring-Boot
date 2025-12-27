package com.booking.eventbookingservice.event.service;

import com.booking.eventbookingservice.event.dto.EventSummaryResponse;
import com.booking.eventbookingservice.event.entity.Event;
import com.booking.eventbookingservice.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventBrowseService {

    private final EventRepository eventRepository;

    public List<EventSummaryResponse> getByLanguage(String language) {
        return eventRepository.findByLanguageIgnoreCase(language)
                .stream()
                .map(this::mapToSummary)
                .toList();
    }

    public List<EventSummaryResponse> getByGenre(String genre) {
        return eventRepository.findByGenreIgnoreCase(genre)
                .stream()
                .map(this::mapToSummary)
                .toList();
    }

    public List<EventSummaryResponse> getByCity(String city) {
        return eventRepository.findEventsByCity(city)
                .stream()
                .map(this::mapToSummary)
                .toList();
    }

    public List<EventSummaryResponse> getByDate(LocalDate date) {
        return eventRepository.findEventsByDate(date)
                .stream()
                .map(this::mapToSummary)
                .toList();
    }

    private EventSummaryResponse mapToSummary(Event event) {
        return EventSummaryResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .language(event.getLanguage())
                .genre(event.getGenre())
                .rating(event.getRating())
                .build();
    }
}