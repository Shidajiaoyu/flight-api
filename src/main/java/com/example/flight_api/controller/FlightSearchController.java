package com.example.flight_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_api.dto.BaseResponse;
import com.example.flight_api.dto.FlightDetailResponseDto;
import com.example.flight_api.dto.FlightSearchRequestDto;
import com.example.flight_api.dto.FlightSearchResponseDto;
import com.example.flight_api.service.FlightSearchService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class FlightSearchController {

    @Autowired
    private FlightSearchService flightSearchService;

    @PostMapping("/flights")
    public BaseResponse<FlightSearchResponseDto> getFlightsInfo(@RequestBody FlightSearchRequestDto flightSearchRequestDto) {

        // call service
        FlightSearchResponseDto reslutList = flightSearchService.findByCondition(flightSearchRequestDto);
        return BaseResponse.success(reslutList);

    }

    @GetMapping("/flights/{id}")
    public BaseResponse<FlightDetailResponseDto> getFlightsDetail(@PathVariable Long id){

        FlightDetailResponseDto flightDetailResponseDto = flightSearchService.getFlightDetailById(id);
        return BaseResponse.success(flightDetailResponseDto);

    }

    //[Request processing failed: java.lang.NullPointerException: Cannot invoke "com.example.flight_api.service.FlightSearchService.getFlightsInfo(com.example.flight_api.dto.FlightSearchRequestDto)" 
    //because "this.flightSearchService" is null] with root cause
    public FlightSearchController(FlightSearchService flightSearchService){
        this.flightSearchService = flightSearchService;
    }

}
