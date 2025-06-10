package com.example.flightapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flightapi.dto.BaseResponse;
import com.example.flightapi.dto.FlightSearchDTO;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.service.FlightService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/search")
    public ResponseEntity<BaseResponse<List<Flight>>> searchFlights(@RequestBody FlightSearchDTO searchDTO) {
        List<Flight> flights = flightService.searchFlights(searchDTO);
        return ResponseEntity.ok(BaseResponse.success(flights));
    }

    @GetMapping("/searchId")
    public ResponseEntity<BaseResponse<Flight>> searchFlightsId(@RequestParam Long id) {
    	Optional<Flight> flights = flightService.searchFlightsId(id);
//        return ResponseEntity.ok(BaseResponse.success(flights));
        if (flights.isPresent()) {
            return ResponseEntity.ok(BaseResponse.success(flights.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponse.error(404,"Flight not found"));
        }
    }
}
