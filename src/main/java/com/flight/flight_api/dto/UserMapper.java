package com.flight.flight_api.dto;

import org.mapstruct.Mapper;

import com.flight.flight_api.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // 把用户信息表里取到的数据转换成用户Dto
    UserDto toUserDto(UserEntity entity);

    // 把用户Dto转换成用户信息表数据
    UserEntity toUserEntity(UserDto dto);
}
