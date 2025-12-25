package com.booking.eventbookingservice.venue.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auditoriums")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditorium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;
}