package com.booking.eventbookingservice.booking.repository;

import com.booking.eventbookingservice.booking.entity.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatStatusRepository extends JpaRepository<ShowSeatStatus, Long> {
    List<ShowSeatStatus> findByShowIdAndSeatIdIn(Long showId, List<Long> seatIds);
}