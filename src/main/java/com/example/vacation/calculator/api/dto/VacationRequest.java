package com.example.vacation.calculator.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class VacationRequest {
    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Positive
    private int averageDailySalary;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getAverageDailySalary() {
        return averageDailySalary;
    }

    public void setAverageDailySalary(int averageDailySalary) {
        this.averageDailySalary = averageDailySalary;
    }
}
