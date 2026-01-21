package com.example.vacation.calculator.api.dto;

public class VacationResponse {

    private final int vacationDays;
    private final int totalAmount;

    public VacationResponse(int vacationDays, int totalAmount) {
        this.vacationDays = vacationDays;
        this.totalAmount = totalAmount;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
