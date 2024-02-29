package com.gateway.app.util;

import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TimeUtil {

    public static Pair<LocalDateTime, LocalDateTime> getDailyStartEndDate(LocalDateTime time) {
        var startDate = time.truncatedTo(ChronoUnit.DAYS);
        var endDate = time.plusDays(1).truncatedTo(ChronoUnit.DAYS);
        return Pair.of(startDate, endDate);

    }

    public static Pair<LocalDateTime, LocalDateTime> getWeeklyStartEndDate(LocalDateTime time) {
        var startDate = time.with(ChronoField.DAY_OF_WEEK, 1).truncatedTo(ChronoUnit.DAYS);
        var endDate = time.with(ChronoField.DAY_OF_WEEK, 7).truncatedTo(ChronoUnit.DAYS);

        return Pair.of(startDate, endDate);
    }

    public static Pair<LocalDateTime, LocalDateTime> getMonthlyStartEndDate(LocalDateTime time) {

        var startDate = time.with(TemporalAdjusters.firstDayOfMonth()).truncatedTo(ChronoUnit.DAYS);
        var endDate = time.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).truncatedTo(ChronoUnit.DAYS);

        return Pair.of(startDate, endDate);
    }

    public static boolean isPeriodActive(LocalDateTime startedAt, LocalDateTime endedAt) {
        var now = LocalDateTime.now();
        return now.isAfter(startedAt) && now.isBefore(endedAt);
    }
}
