package com.flight.flight_api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flight.flight_api.entity.FlightEntity;

// 航班表操作
@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long>,
                JpaSpecificationExecutor<FlightEntity> {

        // 查找满足检索条件的航班信息
        @Query(value = "SELECT f.* " +
                        "FROM flight f " +
                        "INNER JOIN airport d ON f.departure_airport_id = d.airport_id " +
                        "INNER JOIN airport a ON f.destination_airport_id = a.airport_id " +
                        "WHERE d.city = :departure " +
                        "AND a.city = :arrival " +
                        "AND f.departure_date = :departureDate " +
                        "AND f.arrival_date = :arrivalDate", nativeQuery = true)
        List<FlightEntity> findByDepartureCityAndArrivalCityAndDepartureDateBetween(
                        @Param("departure") String departure,
                        @Param("arrival") String arrival,
                        @Param("departureDate") Date departureDate,
                        @Param("arrivalDate") Date arrivalDate);

        // 获取指定id的航班信息
        FlightEntity findByFlightId(Long flightId);
}
