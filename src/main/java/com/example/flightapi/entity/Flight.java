package com.example.flightapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    private String fromAirport;
    private String toAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private String airline;
    private boolean direct;

    private double basePrice;
    private double taxes;

    private String seatClass; // Economy / Business
}
