package com.flight.flight_api.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

// 日期格式转换
@Component
public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // 默认格式转换(yyyy-MM-dd)
    public static Date stringToSqlDate(String dateString) throws ParseException {
        return stringToSqlDate(dateString, DEFAULT_DATE_FORMAT);
    }

    // 自定义格式转换
    public static Date stringToSqlDate(String dateString, String format)
            throws ParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        java.util.Date utilDate = sdf.parse(dateString);
        return new Date(utilDate.getTime());
    }

    // 安全转换(返回null代替抛出异常)
    public static Date safeStringToSqlDate(String dateString) {
        try {
            return stringToSqlDate(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
