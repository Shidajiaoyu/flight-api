package com.flight.flight_api.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.flight.flight_api.entity.BookingEntity;
import com.flight.flight_api.entity.FlightEntity;
import com.flight.flight_api.entity.PassengerEntity;
import com.flight.flight_api.entity.UserEntity;
import com.flight.flight_api.utils.BookingStatus;

@Component
public class BookingMapper {
    @Value("${app.tax-rate:0.00}") // 默认值0.00
    private BigDecimal taxRate;

    public BookingEntity toBookingEntity(AddBookingRequest request, UserEntity user, FlightEntity flight) {
        BookingEntity booking = new BookingEntity();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setReference(generateReferenceNumber()); // 生成唯一参考号
        booking.setStatus(BookingStatus.PENDING.name());
        booking.setTotalPrice(calculateTotalPrice(flight, request.getPassengers())); // 计算总价格(不含税)
        booking.setOtherFee(calculateOtherFee(booking.getTotalPrice())); // 计算其他费用
        booking.setBookingTime(new Timestamp(System.currentTimeMillis()));

        return booking;
    }

    public PassengerEntity toPassengerEntity(PassengerDto passengerDto, BookingEntity booking) {
        PassengerEntity passenger = new PassengerEntity();
        passenger.setBooking(booking);
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());

        return passenger;
    }

    // 生成唯一参考号的方法
    private String generateReferenceNumber() {
        // 实现生成唯一参考号的逻辑
        return "REF-" + System.currentTimeMillis();
    }

    // 计算总价格(不含税)
    private BigDecimal calculateTotalPrice(FlightEntity flight, List<PassengerDto> passengers) {

        // 总价格(不含税)
        BigDecimal totalPrice = BigDecimal.ZERO;
        // 乘客数
        int passengerCount = 0;

        // 出发单价
        if (flight != null && flight.getPrice() != null && passengers != null) {
            totalPrice = flight.getPrice();
            // 乘客数
            passengerCount = passengers.size();
        }

        return totalPrice.multiply(new BigDecimal(passengerCount))
                .setScale(2, RoundingMode.HALF_UP);
    }

    // 计算其他费用
    private BigDecimal calculateOtherFee(BigDecimal baseTotalPrice) {
        // 计算其他费用
        BigDecimal tax = baseTotalPrice.multiply(taxRate)
                .setScale(2, RoundingMode.HALF_UP);

        return tax;
    }

    public BookingDto mapToBookingDto(Object[] row) {
        BookingDto dto = new BookingDto();
        dto.setBookingId(String.valueOf(row[0]));
        dto.setReference((String) row[1]);
        dto.setStatus((String) row[2]);
        dto.setCompleteTime((Timestamp) row[3]);
        dto.setDepartureCity((String) row[4]);
        dto.setArrivalCity((String) row[5]);
        dto.setDepartureDate((Date) row[6]);
        dto.setDepartureTime((Time) row[7]);
        return dto;
    }
}