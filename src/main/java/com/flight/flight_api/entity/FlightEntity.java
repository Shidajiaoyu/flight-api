package com.flight.flight_api.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 航班信息表定义
@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightEntity {

    // 航班id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    // 单程/往返
    @Column(nullable = false, length = 10)
    private String flightNumber;

    // 出发地机场Id
    @Column(nullable = false)
    private Long departureAirportId;

    // 出发日期
    @Column(nullable = false)
    private Date departureDate;

    // 出发时刻
    @Column(nullable = false)
    private Time departureTime;

    // 到达地机场Id
    @Column(nullable = false)
    private Long destinationAirportId;

    // 到达日期
    @Column(nullable = false)
    private Date arrivalDate;

    // 到达时刻
    @Column(nullable = false)
    private Time arrivalTime;

    // 价格
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    // 订单信息
    @OneToMany(mappedBy = "flight")
    private Set<BookingEntity> bookings;
}
