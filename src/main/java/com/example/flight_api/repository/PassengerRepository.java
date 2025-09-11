package com.example.flight_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.flight_api.entity.Passenger;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	@Query(value="select * from passenger p where p.booking_id = :bookingid"
			,nativeQuery = true)
	List<Passenger> findBybookingid(@Param("bookingid") Long booking_id);
}