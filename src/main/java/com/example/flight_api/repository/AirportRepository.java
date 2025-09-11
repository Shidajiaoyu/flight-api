package com.example.flight_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flight_api.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport,Long>{
    
    List<Airport> findAll();

}
