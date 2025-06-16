package com.flight.flight_api.dto;

import com.flight.flight_api.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T11:20:41+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCountry( entity.getCountry() );
        userDto.setEmail( entity.getEmail() );
        userDto.setFirstName( entity.getFirstName() );
        userDto.setLastName( entity.getLastName() );
        userDto.setPassword( entity.getPassword() );
        userDto.setPhone( entity.getPhone() );
        userDto.setUserId( entity.getUserId() );

        return userDto;
    }

    @Override
    public UserEntity toUserEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setCountry( dto.getCountry() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setFirstName( dto.getFirstName() );
        userEntity.setLastName( dto.getLastName() );
        userEntity.setPassword( dto.getPassword() );
        userEntity.setPhone( dto.getPhone() );
        userEntity.setUserId( dto.getUserId() );

        return userEntity;
    }
}
