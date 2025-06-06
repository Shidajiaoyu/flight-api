package com.example.flight_api.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flight_api.dto.FlightDetailDto;
import com.example.flight_api.dto.FlightDetailResponseDto;
import com.example.flight_api.dto.FlightDto;
import com.example.flight_api.dto.FlightSearchRequestDto;
import com.example.flight_api.dto.FlightSearchResponseDto;
import com.example.flight_api.entity.Flight;
import com.example.flight_api.repository.FlightRepository;
import com.example.flight_api.util.DateTimeUtils;

@Service
public class FlightSearchService {

    private FlightRepository flightRepository;

    public FlightSearchResponseDto findByCondition(FlightSearchRequestDto flightSearchRequestDto) {

        List<Flight> flightListForDeparture = flightRepository.findByCondition(
                flightSearchRequestDto.getDepartureAirport(),
                flightSearchRequestDto.getArrivalAirport(), flightSearchRequestDto.getDepartureDate());
        List<FlightDto> departureDtos = convertToDto(flightListForDeparture, flightSearchRequestDto);

        // Determine whether it is one-way or round trip
        if (flightSearchRequestDto.getReturnDate() == null) {
            return new FlightSearchResponseDto(departureDtos);
        }

        List<Flight> flightListForReturn = flightRepository.findByCondition(flightSearchRequestDto.getArrivalAirport(),
                flightSearchRequestDto.getDepartureAirport(),
                flightSearchRequestDto.getReturnDate());
        List<FlightDto> returnDtos = convertToDto(flightListForReturn, flightSearchRequestDto);

        return new FlightSearchResponseDto(departureDtos, returnDtos);
    }

    /**
     * According to flightid, get the flight detail information
     * 
     * @param id flightid
     * @return
     */
    public FlightDetailResponseDto getFlightDetailById(Long id) {

        Object[] flightDetailDepartureObjects = flightRepository.findByConditionForDetail(id);

        if (flightDetailDepartureObjects != null && flightDetailDepartureObjects.length > 0) {
            Object[] departureobjs = (Object[]) flightDetailDepartureObjects[0];
            return convertToDto(departureobjs);
        }
        return null;
    }

    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private List<FlightDto> convertToDto(List<Flight> flights, FlightSearchRequestDto flightSearchRequestDto) {
        return flights.stream().map(flight -> {
            LocalDateTime localDateTimeDep = LocalDateTime.of(flight.getDeparture_date(), flight.getDeparture_time());
            LocalDateTime localDateTimeDes = LocalDateTime.of(flight.getDestination_date(),
                    flight.getDestination_time());
            FlightDto flightDto = new FlightDto();
            flightDto.setId(flight.getFlight_id());
            flightDto.setFlight_number(flight.getFlight_number());
            flightDto.setDeparture(
                    flightSearchRequestDto.getReturnDate() == null ? flightSearchRequestDto.getDepartureAirport()
                            : flightSearchRequestDto.getArrivalAirport());
            flightDto.setDestination(
                    flightSearchRequestDto.getReturnDate() == null ? flightSearchRequestDto.getArrivalAirport()
                            : flightSearchRequestDto.getDepartureAirport());
            flightDto.setDepartureTimestamp(localDateTimeDep);
            flightDto.setDestinationTimestamp(localDateTimeDes);
            flightDto.setDuration(DateTimeUtils.formatTimeDuration(localDateTimeDep, localDateTimeDes));
            flightDto.setPrice(flight.getPrice());
            return flightDto;
        }).toList();
    }

    private FlightDetailResponseDto convertToDto(Object[] flights) {
        FlightDetailResponseDto flightDetailResponseDto = new FlightDetailResponseDto();
        flightDetailResponseDto.setDeparture(flights[0].toString());
        flightDetailResponseDto.setDestination(flights[1].toString());
        LocalDateTime localDateTimeDep = LocalDateTime.of(convertToLocalDate(flights[2]),
                convertToLocalTime(flights[3]));
        LocalDateTime localDateTimeDes = LocalDateTime.of(convertToLocalDate(flights[4]),
                convertToLocalTime(flights[5]));
        flightDetailResponseDto.setDepartureTimestamp(localDateTimeDep);
        flightDetailResponseDto.setDestinationTimestamp(localDateTimeDes);
        flightDetailResponseDto.setPositionlevel(flights[6].toString());
        BigDecimal basefare = (BigDecimal) flights[7];
        BigDecimal taxfare = (BigDecimal) flights[8];
        BigDecimal totalfare = basefare.add(taxfare);
        flightDetailResponseDto.setBasefare(basefare);
        flightDetailResponseDto.setTaxfare(taxfare);
        flightDetailResponseDto.setTotalfare(totalfare);
        return flightDetailResponseDto;
    }

    public static LocalDate convertToLocalDate(Object obj) {
        if (obj instanceof Date) {
            return ((Date) obj).toLocalDate();
        }
        return null;

    }

    public static LocalTime convertToLocalTime(Object obj) {
        if (obj instanceof Time) {
            return ((Time) obj).toLocalTime();
        }
        return null;
    }

    public FlightDetailDto getDetailById(Long id) {
        Object[] flightDetailDepartureObjects = flightRepository.findByConditionForDetail(id);

        if (flightDetailDepartureObjects != null && flightDetailDepartureObjects.length > 0) {
            Object[] departureobjs = (Object[]) flightDetailDepartureObjects[0];
            return convertToDetailDto(departureobjs);
        }
        return new FlightDetailDto();
    }
    
    private FlightDetailDto convertToDetailDto(Object[] flights) {
        LocalDateTime localDateTimeDep = LocalDateTime.of(convertToLocalDate(flights[2]),convertToLocalTime(flights[3]));
        LocalDateTime localDateTimeDes = LocalDateTime.of(convertToLocalDate(flights[4]),convertToLocalTime(flights[5]));
        FlightDetailDto flightDto = new FlightDetailDto();
        flightDto.setDeparture(flights[0].toString());
        flightDto.setDestination(flights[1].toString());
        flightDto.setDepartureTimestamp(localDateTimeDep);
        flightDto.setDestinationTimestamp(localDateTimeDes);
        flightDto.setPosition_level(flights[6].toString());
        return flightDto;
    }
}
