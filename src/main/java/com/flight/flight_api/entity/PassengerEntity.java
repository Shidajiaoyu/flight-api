package com.flight.flight_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 乘客信息表定义
@Entity
@Table(name = "passenger")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerEntity {

    // 乘客ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    // 订单信息
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    BookingEntity booking;

    // 乘客姓名
    @Column(nullable = false, length = 50)
    private String firstName;

    // 乘客姓名
    @Column(nullable = false, length = 50)
    private String lastName;

    // 邮箱
    @Column(nullable = false, length = 100)
    private String email;
}
