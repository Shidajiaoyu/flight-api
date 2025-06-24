package com.example.flight_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_api.dto.AirportResponseDto;
import com.example.flight_api.dto.BaseResponse;
import com.example.flight_api.service.AirportGetService;

@RestController
@RequestMapping("/api")
public class AirportGetController {

    @Autowired
    private AirportGetService airportGetService;

    /**
     * Get all airports information.
     */
    @GetMapping("/airports")
    public BaseResponse<List<AirportResponseDto>> getAirports(){

        List<AirportResponseDto> airportResponseDtoLists = airportGetService.getAirports();

        return BaseResponse.success(airportResponseDtoLists);
    }

    public AirportGetController(AirportGetService airportGetService){
        this.airportGetService = airportGetService;
    }
}
