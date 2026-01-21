package com.example.vacation.calculator.service;

import com.example.vacation.calculator.calendar.HolidayCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class WorkingDaysCounter {
    private final HolidayCalendar holidayCalendar;

    public WorkingDaysCounter(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = holidayCalendar;
    }

    public int countPayableDays(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);

        if (startDate.isAfter(endDate)) throw new IllegalArgumentException("startDate must be <= endDate");
        int count = 0;

        for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
            if (isWeekend(d)) {
                continue;
            }
            if (holidayCalendar.isHoliday(d)) {
                continue;
            }
            count++;
        }
        return count;
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
    }
}
