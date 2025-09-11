package com.example.flight_api.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeUtils {

    public static String formatTimeDuration(LocalDateTime start, LocalDateTime end) {

        Duration duration = Duration.between(start, end).abs();

        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        StringBuilder result = new StringBuilder();

        if (days > 0) {
            result.append(days).append("D");
        }
        if (hours > 0) {
            result.append(hours).append("H");
        }
        if (minutes > 0 || result.isEmpty()) {
            result.append(minutes).append("M");
        }

        return result.toString();
    }
}
