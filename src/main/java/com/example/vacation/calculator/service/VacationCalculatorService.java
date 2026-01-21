package com.example.vacation.calculator.service;

import com.example.vacation.calculator.api.dto.VacationRequest;
import com.example.vacation.calculator.api.dto.VacationResponse;
import com.example.vacation.calculator.calendar.HolidayCalendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class VacationCalculatorService {

    private final HolidayCalendar holidayCalendar;

    public VacationCalculatorService(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = holidayCalendar;
    }

    public VacationResponse calculate(VacationRequest request) {
        Objects.requireNonNull(request, "request must not be null");

        LocalDate start = Objects.requireNonNull(request.getStartDate(), "startDate must not be null");
        LocalDate end = Objects.requireNonNull(request.getEndDate(), "endDate must not be null");

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("startDate must be <= endDate");
        }

        int averageDailySalary = request.getAverageDailySalary();
        if (averageDailySalary <= 0) {
            throw new IllegalArgumentException("averageDailySalary must be > 0");
        }

        WorkingDaysCounter counter = new WorkingDaysCounter(holidayCalendar);
        int payableDays = counter.countPayableDays(start, end);

        int totalAmount = Math.multiplyExact(payableDays, averageDailySalary); // Умножение с защитой от переполнения

        return new VacationResponse(payableDays, totalAmount);
    }
}

