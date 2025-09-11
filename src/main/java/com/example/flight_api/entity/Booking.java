package com.example.flight_api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long booking_id;

    // FK:User.user_id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // FK:FlightEntity.flight_id;
    // @OneToOne
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)
    @JsonBackReference
    private Flight flight;

    @Column(nullable = false, length = 20)
    private String reference;

    // ENUM
    @Column(nullable = false, length = 20)
    private String status;

    // Requirment:create table column type is TIMESTAMP
    // @Temporal(TemporalType.TIMESTAMP) => DATETIME(6)
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime booking_time;

    // Requirment:create table column type is Decimal(10,2)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total_price;

    // @OneToMany(mappedBy = "booking")
    // private Set<Passenger> passengers;
}
