package com.example.vacation.calculator.api;

import com.example.vacation.calculator.api.dto.VacationRequest;
import com.example.vacation.calculator.api.dto.VacationResponse;
import com.example.vacation.calculator.service.VacationCalculatorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class VacationController {

    private final VacationCalculatorService service;

    public VacationController(VacationCalculatorService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public VacationResponse calculate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam int averageDailySalary
    ) {
        VacationRequest request = new VacationRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setAverageDailySalary(averageDailySalary);
        return service.calculate(request);
    }
}