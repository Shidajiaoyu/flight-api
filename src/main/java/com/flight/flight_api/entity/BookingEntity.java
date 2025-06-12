package com.flight.flight_api.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 订单表定义
@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    // Id, 自动赋值
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 订单号
    @Column(nullable = false, length = 20)
    private String bookingId;

    // 用户ID
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // 航班号
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    // 参考号
    @Column(nullable = false, length = 20)
    private String reference;

    // 订单状态
    @Column(nullable = false, length = 20)
    private String status;

    // 下单时间
    @Column(nullable = false)
    private Timestamp bookingTime;

    // 完成时间
    @Column
    private Timestamp completeTime;

    // 总价格(不含税)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    // 税等其他费用总额
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal otherFee;

    // 乘客信息
    @OneToMany(mappedBy = "booking")
    private Set<PassengerEntity> passengers;
}
