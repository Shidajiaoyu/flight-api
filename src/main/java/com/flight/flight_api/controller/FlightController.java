package com.flight.flight_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flight_api.dto.FlightRequest;
import com.flight.flight_api.dto.FlightResponse;
import com.flight.flight_api.service.FlightService;

import jakarta.validation.Valid;

// 航班检索
@RestController
@Validated
@RequestMapping("api/flights")
public class FlightController {

    private final FlightService flightService;

    // 构造体
    // 注入FlightService
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // 获取所有航班信息
    @PostMapping()
    public ResponseEntity<FlightResponse> getAllFlights(@RequestBody @Valid FlightRequest in) {
        // 获取所有航班信息
        FlightResponse out = flightService.getAllFlights(in);

        return ResponseEntity.ok(out);
    }

    // 获取指定航班具体信息
    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightDetail(@PathVariable Long id) {
        // 获取指定航班具体信息
        FlightResponse out = flightService.getFlightDetail(id);

        return ResponseEntity.ok(out);
    }
}
