package com.example.vacation.calculator.service;

import com.example.vacation.calculator.api.dto.VacationResponse;
import com.example.vacation.calculator.calendar.HolidayCalendar;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class VacationCalculatorService {

    private static final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);
    private static final BigDecimal AVG_MONTH_DAYS = new BigDecimal("29.3"); // без new, использует внутри double, может потащить неточность
    private static final int MONEY_SCALE = 0;

    private final HolidayCalendar holidayCalendar;

    public VacationCalculatorService(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = Objects.requireNonNull(holidayCalendar, "holidayCalendar must not be null");
    }

    public VacationResponse calculateByDays(long averageSalary12Months, int vacationDays) {
        validateAverageSalary12Months(averageSalary12Months);
        if (vacationDays <= 0) throw new IllegalArgumentException("vacationDays must be > 0");

        int avgDaily = averageDailyFrom12Months(averageSalary12Months);
        int totalAmount = Math.toIntExact(Math.multiplyExact((long) vacationDays, (long) avgDaily));

        return new VacationResponse(vacationDays, totalAmount);
    }

    public VacationResponse calculateByDates(long averageSalary12Months, LocalDate startDate, LocalDate endDate) {
        validateAverageSalary12Months(averageSalary12Months);
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        if (startDate.isAfter(endDate)) throw new IllegalArgumentException("startDate must be <= endDate");

        WorkingDaysCounter counter = new WorkingDaysCounter(holidayCalendar);
        int payableDays = counter.countPayableDays(startDate, endDate);

        int avgDaily = averageDailyFrom12Months(averageSalary12Months);
        int totalAmount = Math.toIntExact(Math.multiplyExact((long) payableDays, (long) avgDaily));

        return new VacationResponse(payableDays, totalAmount);
    }

    private static void validateAverageSalary12Months(long averageSalary12Months) {
        if (averageSalary12Months <= 0) throw new IllegalArgumentException("averageSalary12Months must be > 0");
    }

    private static int averageDailyFrom12Months(long averageSalary12Months) {
        BigDecimal salary = BigDecimal.valueOf(averageSalary12Months);

        BigDecimal avgMonthly = salary.divide(MONTHS_IN_YEAR, 10, RoundingMode.HALF_UP);
        BigDecimal avgDaily = avgMonthly.divide(AVG_MONTH_DAYS, 10, RoundingMode.HALF_UP);

        BigDecimal money = avgDaily.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
        return money.intValueExact();
    }
}

