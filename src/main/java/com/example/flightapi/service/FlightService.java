package com.example.flightapi.service;

import com.example.flightapi.dto.FlightSearchDTO;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> searchFlights(FlightSearchDTO searchDTO) {
        LocalDate departureDate = LocalDate.parse(searchDTO.getDepartureDate());
        return flightRepository.findByFromAirportAndToAirportAndDepartureDate(
                searchDTO.getFrom(), searchDTO.getTo(), departureDate);
    }
    public Optional<Flight> searchFlightsId(Long id) {

        return flightRepository.findById(id);
        
    }
}
