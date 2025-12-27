package com.booking.eventbookingservice.event.repository;

import com.booking.eventbookingservice.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import java.util.Collection;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLanguageIgnoreCase(String language);

    List<Event> findByGenreIgnoreCase(String genre);

    @Query("""
    SELECT DISTINCT e FROM Event e
    JOIN Show s ON s.event.id = e.id
    JOIN Venue v ON v.id = s.auditorium.venue.id
    WHERE LOWER(v.city) = LOWER(:city)
""")
    List<Event> findEventsByCity(@Param("city") String city);

    @Query("""
    SELECT DISTINCT e FROM Event e
    JOIN Show s ON s.event.id = e.id
    WHERE DATE(s.startTime) = :date
""")
    List<Event> findEventsByDate(@Param("date") LocalDate date);

}