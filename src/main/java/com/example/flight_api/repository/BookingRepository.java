package com.example.flight_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.flight_api.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // get bookings
    // @Query(value = "SELECT * FROM Booking bk " +
    // " WHERE bk.user_id = :userId" +
    // " and bk.status = :status" +
    // " order by" +
    // " bk.booking_time asc ",
    // nativeQuery = true)
    // Page<Booking> findByUserIdandStatus(@Param("userId") Long userId,
    // @Param("status") String status, Pageable pageable);

    @Query(value = "SELECT * FROM booking bk " +
            "WHERE bk.user_id = :userId AND bk.status = :status " +
            "ORDER BY bk.booking_time ASC", countQuery = "SELECT count(*) FROM Booking bk WHERE bk.user_id = :userId AND bk.status = :status", nativeQuery = true)
    Page<Booking> findByUserIdandStatus(
            @Param("userId") Long userId,
            @Param("status") String status,
            Pageable pageable);

    @Query(value = "SELECT * FROM booking bk WHERE bk.user_id = :userId AND bk.status = :status ORDER BY bk.booking_time ASC", nativeQuery = true)
    List<Booking> testFind(@Param("userId") Long userId, @Param("status") String status);

}
