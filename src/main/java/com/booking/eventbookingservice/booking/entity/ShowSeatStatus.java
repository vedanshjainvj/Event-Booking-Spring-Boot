package com.booking.eventbookingservice.booking.entity;

import com.booking.eventbookingservice.seat.entity.Seat;
import com.booking.eventbookingservice.show.entity.Show;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "show_seat_status",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"show_id", "seat_id"}
        )
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatState state;
}