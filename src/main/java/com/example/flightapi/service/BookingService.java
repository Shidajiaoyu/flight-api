package com.example.flightapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.flightapi.dto.BookingResponseDTO;
import com.example.flightapi.entity.Booking;
import com.example.flightapi.entity.Flight;
import com.example.flightapi.entity.User;
import com.example.flightapi.repository.BookingRepository;
import com.example.flightapi.repository.FlightRepository;
import com.example.flightapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public List<BookingResponseDTO> getBookingsByUser(String email, String status) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Booking> bookings = bookingRepository.findByUserAndStatus(user, status);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Booking createBooking(String email, Long flightId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Booking booking = Booking.builder()
                .referenceCode(null) // prePersist 会生成
                .user(user)
                .flight(flight)
                .status("Upcoming")
                .build();
        return bookingRepository.save(booking);
    }

    private BookingResponseDTO convertToDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setReferenceCode(booking.getReferenceCode());
        dto.setFromAirport(booking.getFlight().getFromAirport());
        dto.setToAirport(booking.getFlight().getToAirport());
        dto.setDepartureTime(booking.getFlight().getDepartureTime());
        dto.setBookingTime(booking.getCreatedAt());
        dto.setStatus(booking.getStatus());
        return dto;
    }
    
    public Booking getBookingsByUser(Long bookingId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Flight flight = flightRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Booking booking = bookingRepository.findByUserAndFlight( user, flight);
        return booking;
    }

   }
