package com.example.flight_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flight_api.dto.AirportResponseDto;
import com.example.flight_api.entity.Airport;
import com.example.flight_api.repository.AirportRepository;

@Service
public class AirportGetService {

    @Autowired
    private AirportRepository airportRepository;

    public List<AirportResponseDto> getAirports() {
        List<Airport> airports = airportRepository.findAll();

        List<AirportResponseDto> lists = new ArrayList<>();
        for (Airport airport : airports) {
            AirportResponseDto dto = new AirportResponseDto();
            dto.setCity(airport.getCity());
            dto.setCode(airport.getCode());
            dto.setName(airport.getName());
            lists.add(dto);
        }
        return lists;
    }

    public AirportGetService(AirportRepository airportRepository){
        this.airportRepository = airportRepository;
    }
}
