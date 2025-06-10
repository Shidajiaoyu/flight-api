package com.example.flightapi.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightapi.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    // 查询指定日期当天出发的航班
    default List<Flight> findByFromAirportAndToAirportAndDepartureDate(
            String fromAirport, String toAirport, LocalDate departureDate) {
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = departureDate.atTime(23, 59, 59);
        return findByFromAirportAndToAirportAndDepartureTimeBetween(
                fromAirport, toAirport, startOfDay, endOfDay);
    }

    List<Flight> findByFromAirportAndToAirportAndDepartureTimeBetween(
            String fromAirport, String toAirport, LocalDateTime start, LocalDateTime end);
    
}
