package com.flight.flight_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flight.flight_api.entity.BookingEntity;

// 订单表操作
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>,
                JpaSpecificationExecutor<BookingEntity> {

        // 获取以往的订单信息
        @Query(value = """
                        SELECT b.booking_id, b.reference, b.status, b.complete_time,
                        d.city, a.city, f.departure_date, f.departure_time FROM booking b
                        INNER JOIN flight f ON b.flight_id = f.flight_id
                        INNER JOIN airport d ON f.departure_airport_id = d.airport_id
                        INNER JOIN airport a ON f.destination_airport_id = a.airport_id
                        WHERE b.complete_time IS NULL""", nativeQuery = true)
        Page<Object[]> findIncompleteBookings(Pageable pageable);

        // 获取以往的订单信息
        @Query(value = """
                        SELECT b.booking_id, b.reference, b.status, b.complete_time,
                        d.city, a.city, f.departure_date, f.departure_time FROM booking b
                        INNER JOIN flight f ON b.flight_id = f.flight_id
                        INNER JOIN airport d ON f.departure_airport_id = d.airport_id
                        INNER JOIN airport a ON f.destination_airport_id = a.airport_id
                        WHERE b.complete_time IS NOT NULL""", nativeQuery = true)
        Page<Object[]> findCompletedBookings(Pageable pageable);

        // 根据订单号查找订单
        List<BookingEntity> findByBookingId(String bookingId);
}
