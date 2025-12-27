package com.booking.eventbookingservice.show.repository;

import com.booking.eventbookingservice.show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByEventId(Long eventId);

    List<Show> findByAuditoriumId(Long auditoriumId);
}