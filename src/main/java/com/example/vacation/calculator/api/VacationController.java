package com.example.vacation.calculator.api;

import com.example.vacation.calculator.api.dto.VacationResponse;
import com.example.vacation.calculator.service.VacationCalculatorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;


@RestController
public class VacationController {

    private final VacationCalculatorService service;

    public VacationController(VacationCalculatorService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public VacationResponse calculate(
            // Обязательный параметр
            @RequestParam long averageSalary12Months,

            // режим 1 (минимальный)
            @RequestParam(required = false) Integer vacationDays,

            // режим 2 (по датам)
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        try {
            boolean hasDates = (startDate != null || endDate != null);
            boolean hasDays = (vacationDays != null);

            if (hasDates && hasDays) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Provide either vacationDays OR (startDate & endDate), not both"
                );
            }

            // Два расчёта
            if (hasDates) {
                if (startDate == null || endDate == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Both startDate and endDate must be provided"
                    );
                }
                return service.calculateByDates(averageSalary12Months, startDate, endDate);
            }

            if (hasDays) {
                return service.calculateByDays(averageSalary12Months, vacationDays);
            }

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Provide vacationDays OR (startDate & endDate)"
            );
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (ArithmeticException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount overflow", ex);
        }
    }
}