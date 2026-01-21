package com.example.vacation.calculator.calendar;

import java.time.LocalDate;

public interface HolidayCalendar {
    boolean isHoliday(LocalDate date);
}
