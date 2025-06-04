package com.flight.flight_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "airport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String city;
}
