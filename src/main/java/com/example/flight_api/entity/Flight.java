package com.example.flight_api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flight_id;

    @Column(nullable = false, length = 10)
    private String flight_number;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate departure_date;

    // Specify precision (TIME (0) represents second level precision)
    @Column(nullable = false, columnDefinition = "TIME(0)")
    private LocalTime departure_time;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate destination_date;

    @Column(nullable = false, columnDefinition = "TIME(0)")
    private LocalTime destination_time;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tax;

    @Column(nullable = false, length = 20)
    private String position_level;

    // FK:departure_airport_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "airport_id", nullable = false)
    private Airport airportEntity_departure;

    // FK:destination_airport_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_id", referencedColumnName = "airport_id", nullable = false)
    private Airport airportEntity_destination;

    // @OneToOne(mappedBy = "flight") > create a constraint that a flight can only have one bookin
    // private Booking booking;
    @OneToMany(mappedBy = "flight")
	private Set<Booking> bookings;

}
