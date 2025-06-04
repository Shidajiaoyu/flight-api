package com.flight.flight_api.entity;

import java.math.BigDecimal;
import java.security.Timestamp;
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
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @Column(nullable = false, length = 20)
    private String reference;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private Timestamp bookingTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "booking")
    private Set<PassengerEntity> passengers;
}
