package com.example.vacation.calculator.service;

import com.example.vacation.calculator.calendar.HolidayCalendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VacationCalculatorServiceTest {

    @Test
    void calculateByDays_returnsAmountForGivenVacationDays() {
        HolidayCalendar calendar = date -> false;
        VacationCalculatorService service = new VacationCalculatorService(calendar);

        // 600000/12 = 50000; 50000/29.3 ≈ 1706 (HALF_UP)
        var res = service.calculateByDays(600_000L, 14);

        assertEquals(14, res.getVacationDays());
        assertEquals(14 * 1706, res.getTotalAmount());
    }

    @Test
    void calculateByDates_excludesWeekendsAndHolidays() {
        Set<LocalDate> holidays = Set.of(LocalDate.of(2026, 1, 7));
        HolidayCalendar calendar = holidays::contains;

        VacationCalculatorService service = new VacationCalculatorService(calendar);

        var res = service.calculateByDates(
                600_000L,
                LocalDate.of(2026, 1, 5),
                LocalDate.of(2026, 1, 9)
        );

        assertEquals(4, res.getVacationDays());
        assertEquals(4 * 1706, res.getTotalAmount());
    }
}
