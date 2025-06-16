package com.flight.flight_api.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flight.flight_api.dto.AddBookingRequest;
import com.flight.flight_api.dto.AddBookingResponse;
import com.flight.flight_api.dto.BookingDto;
import com.flight.flight_api.dto.BookingMapper;
import com.flight.flight_api.dto.PassengerDto;
import com.flight.flight_api.dto.UpdateBookingStatusResponse;
import com.flight.flight_api.entity.BookingEntity;
import com.flight.flight_api.entity.FlightEntity;
import com.flight.flight_api.entity.PassengerEntity;
import com.flight.flight_api.entity.UserEntity;
import com.flight.flight_api.exception.ServiceException;
import com.flight.flight_api.repository.BookingRepository;
import com.flight.flight_api.repository.FlightRepository;
import com.flight.flight_api.repository.PassengerRepository;
import com.flight.flight_api.repository.UserRepository;
import com.flight.flight_api.utils.AppConstants;
import com.flight.flight_api.utils.BookingStatus;

// 订单相关操作
@Service
public class BookingService {

    // 用户表操作
    private final UserRepository userRepository;
    // 乘客信息表操作
    private final PassengerRepository passengerRepository;
    // 航班信息表操作
    private final FlightRepository flightRepository;
    // 订单表操作
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    // 构造体
    // 注入BookingRepository等
    public BookingService(UserRepository userRepository,
            PassengerRepository passengerRepository,
            FlightRepository flightRepository,
            BookingRepository bookingRepository,
            BookingMapper bookingMapper) {
        this.userRepository = userRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    // 创建订单
    @Transactional
    public AddBookingResponse createBooking(AddBookingRequest request) {

        // 往返订单信息
        List<BookingEntity> bookings = new ArrayList<>();

        // 总价格(不含税)
        BigDecimal totalPrice = BigDecimal.ZERO;

        // 税等其他费用总额
        BigDecimal otherFee = BigDecimal.ZERO;

        // 1. 验证并获取用户
        Optional<UserEntity> userEntity = userRepository.findById(request.getUserId());

        // 用户不存在
        if (!userEntity.isPresent()) {
            throw new ServiceException(3000, "User not found‌");
        }

        // 2. 验证并获取出发航班
        // 验证出发航班是否存在且价格有效
        Long departureFlightId = request.getDepartureFlightId();
        if (departureFlightId == null) {
            throw new ServiceException(3001, "Invalid departure flight information");
        }

        // 获取出发航班
        Optional<FlightEntity> flightEntity = flightRepository.findById(departureFlightId);

        // 出发航班不存在
        if (!flightEntity.isPresent()) {
            throw new ServiceException(3002, "Departure flight not found‌");
        }

        // 3. 创建出发订单
        BookingEntity departureBooking = bookingMapper.toBookingEntity(request, userEntity.get(), flightEntity.get());
        String bookingId = generateBookingId();
        departureBooking.setBookingId(bookingId);
        departureBooking = bookingRepository.save(departureBooking);
        bookings.add(departureBooking);
        totalPrice = departureBooking.getTotalPrice();
        otherFee = departureBooking.getOtherFee();

        // 4. 如果有返程航班,验证并获取返程航班
        Long retrunFlightId = request.getRetrunFlightId();
        if (retrunFlightId != null) {
            flightEntity = flightRepository.findById(retrunFlightId);

            // 返程航班不存在
            if (!flightEntity.isPresent()) {
                throw new ServiceException(3004, "Return flight not found‌");
            }

            // 5. 创建返程订单
            BookingEntity returnBooking = bookingMapper.toBookingEntity(request, userEntity.get(), flightEntity.get());
            returnBooking.setBookingId(bookingId);
            returnBooking = bookingRepository.save(returnBooking);
            bookings.add(returnBooking);
            totalPrice = totalPrice.add(returnBooking.getTotalPrice());
            otherFee = otherFee.add(returnBooking.getOtherFee());
        }

        // 6.验证乘客列表不为空
        if (request.getPassengers() == null || request.getPassengers().isEmpty()) {
            throw new ServiceException(3005, "No passengers provided");
        }

        // 7.创建乘客记录
        for (PassengerDto passengerDto : request.getPassengers()) {
            PassengerEntity passenger = bookingMapper.toPassengerEntity(passengerDto, departureBooking);
            passengerRepository.save(passenger);
        }

        // 返回订单
        AddBookingResponse res = new AddBookingResponse();
        res.setBookingId(bookingId);
        res.setTotalPrice(totalPrice);
        res.setOtherFee(otherFee);
        return res;
    }

    private String generateBookingId() {
        // 实现订单号生成逻辑，例如: ORD20230610123456
        return "ORD" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    // 查询订单
    @Transactional
    public Page<BookingDto> fetchBookings(String status, int page, int size) {

        Page<Object[]> result;

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("f.departure_date").ascending());

        // 查询未完成订单
        if (AppConstants.FETCH_BOOKINGS_INCOMPLETE.equals(status)) {
            result = bookingRepository.findIncompleteBookings(pageable);
        } else {
            // 查询已完成订单
            result = bookingRepository.findCompletedBookings(pageable);
        }

        return result.map(bookingMapper::mapToBookingDto);
    }

    // 更新订单状态
    public UpdateBookingStatusResponse updateBookingStatus(String bookingId, BookingStatus newStatus) {

        // 查询订单
        List<BookingEntity> bookingEntities = bookingRepository.findByBookingId(bookingId);
        List<BookingDto> bookings = new ArrayList<BookingDto>();

        // 检查订单是否存在
        if (bookingEntities.isEmpty()) {
            throw new ServiceException(3006, "Booking not found with number: " + bookingId);
        }

        // 获取订单实体
        BookingEntity bookingEntity = bookingEntities.get(0);

        // 订单状态流转验证
        String currentStatusStr = bookingEntity.getStatus();
        BookingStatus currentStatus = BookingStatus.fromValue(currentStatusStr);
        BookingStatusMachine.validateTransition(currentStatus, newStatus);

        // 更新订单状态
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        for (BookingEntity entity : bookingEntities) {
            entity.setStatus(newStatus.getValue());
            // 订单完成的时候更新完成时间
            if (BookingStatus.DELIVERED.equals(newStatus)) {
                entity.setCompleteTime(currentTime);
            }
            bookingRepository.save(entity);
            bookings.add(bookingMapper.toBookingDto(entity));
        }

        UpdateBookingStatusResponse res = new UpdateBookingStatusResponse();
        res.setBookingId(bookingId);

        return res;
    }
}
