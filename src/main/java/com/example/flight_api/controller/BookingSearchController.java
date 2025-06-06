package com.example.flight_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_api.dto.BaseResponse;
import com.example.flight_api.dto.BookingResponseDto;
import com.example.flight_api.service.BookingSearchService;

@RestController
@RequestMapping("/api")
public class BookingSearchController {

    @Autowired
    private BookingSearchService bookingSearchService;

    @GetMapping("/bookings")
    public BaseResponse<BookingResponseDto> getBookings(@RequestParam String username,
            @RequestParam int page,
            @RequestParam int size) {

        // 获取订单
        BookingResponseDto response = bookingSearchService.getUserBookings(username, page, size);

        return BaseResponse.success(response);
    }

}
