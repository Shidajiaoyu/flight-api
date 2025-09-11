package com.example.flight_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_api.dto.BaseResponse;
import com.example.flight_api.dto.BookingCreateRequestDto;
import com.example.flight_api.dto.BookingCreateResponseDto;
import com.example.flight_api.dto.BookingResponseDto;
import com.example.flight_api.service.BookingSearchService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class BookingSearchController {

    @Autowired
    private BookingSearchService bookingSearchService;

    /**
     * Get user's bookings information.
     * @param username
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/bookings")
    public BaseResponse<BookingResponseDto> getBookings(@RequestParam String username,
            @RequestParam int page,
            @RequestParam int size) {

        // get bookings by username
        BookingResponseDto response = bookingSearchService.getUserBookings(username, page, size);

        return BaseResponse.success(response);
    }

    /**
     * Create a new booking.
     * @param bookingCreateRequestDto
     * @return result of booking creation
     */
    @PostMapping("/booking/confirm")
    public BaseResponse<BookingCreateResponseDto> createBooking(@RequestBody BookingCreateRequestDto bookingCreateRequestDto) {

        // create booking
        BookingCreateResponseDto response = bookingSearchService.createBooking(bookingCreateRequestDto);
        return BaseResponse.success(response);
    }

}
