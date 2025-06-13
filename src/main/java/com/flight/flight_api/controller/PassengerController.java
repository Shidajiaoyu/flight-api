package com.flight.flight_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.flight.flight_api.entity.PassengerEntity;
import com.flight.flight_api.service.PassengerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<List<PassengerEntity>> getAllUsers() {
        List<PassengerEntity> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @PostMapping("/create")
    public ResponseEntity<PassengerEntity> createUsers(@Valid @RequestBody PassengerEntity passenger) {
        PassengerEntity newPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.status(201).body(newPassenger);
    }
}
