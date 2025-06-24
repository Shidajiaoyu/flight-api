package com.example.flight_api.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateRequestDto {

    private String username;

    private String flightId;

    private BigDecimal totalPrice;
}
