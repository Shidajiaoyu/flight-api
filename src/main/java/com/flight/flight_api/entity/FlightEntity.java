package com.flight.flight_api.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @Column(nullable = false, length = 10)
    private String flightNumber;

    @Column(nullable = false)
    private Long departureAirportId;

    @Column(nullable = false)
    private Long destinationAirportId;

    @Column(nullable = false)
    private Date departureDate;

    @Column(nullable = false)
    private Time departureTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "flight")
    private Set<BookingEntity> bookings;
}
