package com.example.vacation.calculator.api.dto;

import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class VacationRequest {

    @Positive
    private long averageSalary12Months; // сумма за 12 месяцев (обязательно)

    private Integer vacationDays;

    private LocalDate startDate;
    private LocalDate endDate;

    public long getAverageSalary12Months() {
        return averageSalary12Months;
    }

    public void setAverageSalary12Months(long averageSalary12Months) {
        this.averageSalary12Months = averageSalary12Months;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }

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
}
