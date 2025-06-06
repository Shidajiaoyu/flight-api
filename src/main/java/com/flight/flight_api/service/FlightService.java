package com.flight.flight_api.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flight.flight_api.dto.FlightDto;
import com.flight.flight_api.dto.FlightMapper;
import com.flight.flight_api.dto.FlightRequest;
import com.flight.flight_api.dto.FlightResponse;
import com.flight.flight_api.entity.FlightEntity;
import com.flight.flight_api.exception.ServiceException;
import com.flight.flight_api.repository.FlightRepository;
import com.flight.flight_api.utils.DateUtil;

// 航班检索相关操作
@Service
public class FlightService {

    // 航班表操作
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    // 构造体
    // 注入FlightRepository等
    public FlightService(FlightRepository flightRepository,
            FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    // 检索航班信息
    @Transactional
    public FlightResponse getAllFlights(FlightRequest req) {
        List<FlightDto> flightDtos = flightRepository
                .findByDepartureCityAndArrivalCityAndDepartureDateBetween(req.getDeparture(),
                        req.getArrival(), DateUtil.safeStringToSqlDate(req.getDepartDate()),
                        DateUtil.safeStringToSqlDate(req.getReturnDate()))
                .stream()
                .map(flight -> flightMapper.toFlightDto(flight))
                .collect(Collectors.toList());

        // 没有取到任何信息
        if (flightDtos == null || flightDtos.size() == 0)
            throw new ServiceException(3000, "No flight information available");

        FlightResponse res = new FlightResponse();
        res.setFlights(flightDtos);
        return res;
    }

    // 检索指定航班的信息
    @Transactional
    public FlightResponse getFlightDetail(Long id) {
        FlightEntity entity = flightRepository.findByFlightId(id);

        // 没有取到任何信息
        if (entity == null)
            throw new ServiceException(3000, "No flight information available");

        FlightDto dto = flightMapper.toFlightDto(entity);
        FlightResponse res = new FlightResponse();
        res.setFlights(Collections.singletonList(dto));

        return res;
    }
}
