package com.example.flight_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.flight_api.dto.BookingDto;
import com.example.flight_api.dto.BookingResponseDto;
import com.example.flight_api.dto.FlightDetailDto;
import com.example.flight_api.entity.Booking;
import com.example.flight_api.entity.Flight;
import com.example.flight_api.entity.User;
import com.example.flight_api.enums.BookingStatusEnum;
import com.example.flight_api.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingSearchService {

    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final FlightSearchService flightSearchService;

    @Transactional(readOnly = true)
    public BookingResponseDto getUserBookings(String username, int page, int size) {

        // get userid
        User user = userService.getUserInfo(username);

        Pageable pageable = PageRequest.of(page, size);
        return getAllBookings(user.getId(), pageable);

    }

    // return upcoming and pasted bookings
    public BookingResponseDto getAllBookings(Long userId, Pageable pageable) {
        Page<Booking> upcomingPage = bookingRepository.findByUserIdandStatus(userId,
                BookingStatusEnum.UPCOMING.toString(), pageable);
        List<BookingDto> upcomingList = upcomingPage.getContent().stream()
                .map(this::convertToUpcomingDto)
                .collect(Collectors.toList());

        Page<Booking> pastPage = bookingRepository.findByUserIdandStatus(userId, BookingStatusEnum.PASTED.toString(),
                pageable);
        List<BookingDto> pastList = pastPage.getContent().stream()
                .map(this::convertToPastDTO)
                .collect(Collectors.toList());

        return new BookingResponseDto(
                upcomingList,
                pastList,
                pastPage.getNumber(),
                pastPage.getTotalPages(),
                pastPage.getTotalElements(),
                pastPage.getSize(),
                pastPage.hasNext(),
                pastPage.hasPrevious());
    }

    // return upcoming bookings
    public BookingResponseDto getUpcomingBookings(Long userId, String status, Pageable pageable) {
        Page<Booking> upcomingPage = bookingRepository.findByUserIdandStatus(userId, status, pageable);
        List<BookingDto> upcoming = upcomingPage.getContent().stream()
                .map(this::convertToUpcomingDto)
                .collect(Collectors.toList());

        return new BookingResponseDto(
                upcoming,
                List.of(),
                upcomingPage.getNumber(),
                upcomingPage.getTotalPages(),
                upcomingPage.getTotalElements(),
                upcomingPage.getSize(),
                upcomingPage.hasNext(),
                upcomingPage.hasPrevious());
    }

    // upcoming
    private BookingDto convertToUpcomingDto(Booking booking) {
        Flight flight = booking.getFlight();
        FlightDetailDto flightDetailDto = flightSearchService.getDetailById(flight.getFlight_id());
        return new BookingDto(
                booking.getBooking_id(),
                booking.getReference(),
                concatString(flightDetailDto.getDeparture(), flightDetailDto.getDestination()),
                flightDetailDto.getDepartureTimestamp());
    }

    // return upcoming bookings
    public BookingResponseDto getPastedBookings(Long userId, String status, Pageable pageable) {
        Page<Booking> pastPage = bookingRepository.findByUserIdandStatus(userId, status, pageable);
        List<BookingDto> pastList = pastPage.getContent().stream()
                .map(this::convertToPastDTO)
                .collect(Collectors.toList());

        return new BookingResponseDto(
                List.of(),
                pastList,
                pastPage.getNumber(),
                pastPage.getTotalPages(),
                pastPage.getTotalElements(),
                pastPage.getSize(),
                pastPage.hasNext(),
                pastPage.hasPrevious());
    }

    // past
    private BookingDto convertToPastDTO(Booking booking) {
        Flight flight = booking.getFlight();
        FlightDetailDto flightDetailDto = flightSearchService.getDetailById(flight.getFlight_id());
        return new BookingDto(
                booking.getBooking_id(),
                booking.getReference(),
                concatString(flightDetailDto.getDeparture(), flightDetailDto.getDestination()),
                flightDetailDto.getDestinationTimestamp());

    }

    private String concatString(String der, String des) {
        return der.concat(" To ").concat(des);
    }

}
