package com.flight.flight_api.service;

import java.util.EnumMap;
import java.util.Set;

import com.flight.flight_api.utils.BookingStatus;

public class BookingStatusMachine {

    // 定义状态流转规则
    private static final EnumMap<BookingStatus, Set<BookingStatus>> STATUS_TRANSITIONS = new EnumMap<>(
            BookingStatus.class);

    static {
        // 初始化状态流转规则
        STATUS_TRANSITIONS.put(BookingStatus.PENDING,
                Set.of(BookingStatus.CONFIRMED, BookingStatus.CANCELLED));

        STATUS_TRANSITIONS.put(BookingStatus.CONFIRMED,
                Set.of(BookingStatus.SHIPPED, BookingStatus.CANCELLED));

        STATUS_TRANSITIONS.put(BookingStatus.SHIPPED,
                Set.of(BookingStatus.DELIVERED, BookingStatus.CANCELLED));

        // 最终状态不允许再转换
        STATUS_TRANSITIONS.put(BookingStatus.DELIVERED, Set.of());
        STATUS_TRANSITIONS.put(BookingStatus.CANCELLED, Set.of());
    }

    /**
     * 验证状态流转是否合法
     * 
     * @param current 当前状态
     * @param next    目标状态
     * @return 是否允许转换
     */
    public static boolean isValidTransition(BookingStatus current, BookingStatus next) {
        if (current == null || next == null) {
            return false;
        }
        return STATUS_TRANSITIONS.get(current).contains(next);
    }

    /**
     * 验证并抛出异常
     * 
     * @param current 当前状态
     * @param next    目标状态
     * @throws IllegalStateException 如果转换不合法
     */
    public static void validateTransition(BookingStatus current, BookingStatus next) {
        if (!isValidTransition(current, next)) {
            throw new IllegalStateException(
                    String.format("不允许从[%s]状态变更为[%s]状态",
                            current.getValue(),
                            next.getValue()));
        }
    }
}
