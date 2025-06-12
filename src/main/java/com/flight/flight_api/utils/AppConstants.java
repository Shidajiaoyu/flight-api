package com.flight.flight_api.utils;

/**
 * 系统常量定义类
 */
public final class AppConstants {

    private AppConstants() {
        // 防止实例化
    }

    // 订单查询状态
    // 未完成
    public static final String FETCH_BOOKINGS_INCOMPLETE = "Upcoming";
    // 已完成
    public static final String FETCH_BOOKINGS_COMPLETED = "Past";

    // 分页默认值
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;

}
