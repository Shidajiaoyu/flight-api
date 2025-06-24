package com.example.flight_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.flight_api.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "select * from flight f " +
            "where " +
            "f.departure_airport_id = (select airport_id from airport a where city=?1) " +
            "and f.destination_airport_id = (select airport_id from airport b where city=?2) " +
            "and f.departure_date=?3", nativeQuery = true)
    List<Flight> findByCondition(String fromCity, String toCity, LocalDate data);

    @Query(value = "select" +
            " dep.city," +
            " des.city," +
            " f.departure_date," +
            " f.departure_time," +
            " f.destination_date," +
            " f.destination_time," +
            " f.position_level," +
            " f.price," +
            " f.tax," +
            " f.flight_id "+
            " from" +
            " flight f " +
            " inner join airport dep on" +
            " f.departure_airport_id = dep.airport_id" +
            " inner join airport des on " +
            " f.destination_airport_id = des.airport_id " +
            " where" +
            " flight_id = ?1", nativeQuery = true)
    Object[] findByConditionForDetail(Long id);

    @Query(value = "select * from flight f where  f.flight_id = ?1", nativeQuery = true)
    Flight findByFlight_id(Long flight_id);

}
