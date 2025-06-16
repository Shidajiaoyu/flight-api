package com.flight.flight_api.dto;

import org.mapstruct.Mapper;

import com.flight.flight_api.entity.FlightEntity;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    // 把航班表里取到的数据转换成航班Dto
    FlightDto toFlightDto(FlightEntity entity);

    // 把航班Dto转换成航班表数据
    FlightEntity toFlightEntity(FlightDto dto);
}
