package com.example.flightapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightapi.entity.Booking;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserAndStatus(User user, String status);
    
    Booking findByUserAndFlight(User user, Flight flight);
}
