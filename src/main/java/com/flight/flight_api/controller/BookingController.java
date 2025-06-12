package com.flight.flight_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.flight_api.dto.AddBookingRequest;
import com.flight.flight_api.dto.AddBookingResponse;
import com.flight.flight_api.dto.BookingDto;
import com.flight.flight_api.dto.PageResponse;
import com.flight.flight_api.service.BookingService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

// 订单添加/查询
@RestController
@Validated
@RequestMapping("api/bookings")
public class BookingController {
    private final BookingService bookingService;

    // 构造体
    // 注入BookingService
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // 添加订单
    @PostMapping
    public ResponseEntity<AddBookingResponse> createBooking(@Valid @RequestBody AddBookingRequest request) {
        // 添加订单信息
        AddBookingResponse booking = bookingService.createBooking(request);

        return ResponseEntity.ok(booking);
    }

    // 查询订单信息
    @GetMapping
    public ResponseEntity<PageResponse<BookingDto>> fetchBookings(
            @RequestParam(required = false, defaultValue = "Upcoming") @Pattern(regexp = "Upcoming|Past", message = "Status must be Upcoming or Past") String status,
            @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = "Page must be greater than or equal to 1") int page,
            @RequestParam(required = false, defaultValue = "5") @Min(value = 1, message = "Size must be greater than or equal to 1") int size) {
        // 查询订单信息
        Page<BookingDto> result = bookingService.fetchBookings(status, page, size);

        return ResponseEntity.ok(PageResponse.from(result));
    }
}
